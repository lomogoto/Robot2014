package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Watchdog;

public class Robot extends SimpleRobot {
    //initialize objects for use in all methods
    public Message message=new Message();
    public Drive drive=new Drive(C.LEFTDRIVE, C.RIGHTDRIVE);
    public Motor arm=new Motor(C.ARM);
    public Motor roller=new Motor(C.ROLLER);
    public Stick lStick=new Stick(C.LEFTSTICK);
    public Stick rStick=new Stick(C.RIGHTSTICK);
    public Motor shooter=new Motor(C.SHOOTERSCREWPORT);
    public Switch shooterfront=new Switch(C.SHOOTERFRONTSWITCH);
    public Switch shooterback=new Switch(C.SHOOTERBACKSWITCH);
    public Switch armStopSwitch=new Switch(C.ARMSTOPSWITCH);
    //public Switch testSwitch=new Switch(4);
    public boolean loadingBackward;
    public boolean loadingForward;
    
    //call once at turn on
    public void robotInit(){
        test();
        Time.sleep(3);
        message.clear();
        Time.sleep(1);
        loadingForward=false;
        loadingBackward=false;
    }
    
    //run at autonomous
    public void autonomous(){
        message.print(0,"Autonomous");
        drive.set(0.5, 0.5);
        Time.sleep(C.AUTODRIVETIME );
        drive.set(0,0);/*
        shooter.setSpeed(1);
        Time.sleep(C.AUTOPULLBACKTIME);
        shooter.setSpeed(0);
        loadingForward=true;*/
        message.print(0,"Done");
    }
    
    //run with user control
    public void operatorControl(){
        //Watchdog.getInstance().setEnabled(false);
        while(isEnabled()&&isOperatorControl()){
            //print data to screen
            message.debug(0,"Left Stick",""+lStick.getY());
            message.debug(1,"Right Stick",""+rStick.getY());
            message.debug(2,"Ready to shoot",""+(!loadingBackward&&!loadingForward));
            //message.debug(3, "Test Switch",""+testSwitch.get());
            
            //drive
            drive.setDirect(lStick.getY(), rStick.getY());
            
            //test value sped set by throttle
            roller.setSpeed(rStick.getThrottle());
            
            //move arm up and down with buttons 
            if (rStick.getState(C.ARMUPBTN)){
                arm.setSpeed(1);
                if (!(lStick.getState(C.ROLLERUPBTN) || lStick.getState(C.ROLLERDOWNBTN))){
                    roller.setSpeed(1);
                }
            } else if (rStick.getState(C.ARMDOWNBTN)  && !armStopSwitch.get()){
                //if (!armStopSwitch.get()){
                    arm.setSpeed(-1);
                /*} else {
                    arm.setSpeed(0);
                }*/
                if (!(lStick.getState(C.ROLLERUPBTN) || lStick.getState(C.ROLLERDOWNBTN))){
                    roller.setSpeed(-1);
                }
                
            //stop by default
            } else {
                arm.setSpeed(0);
            }
            
            //spin roller with buttons
            if (lStick.getState(C.ROLLERUPBTN)){
                roller.setSpeed(1);
            } else if (lStick.getState(C.ROLLERDOWNBTN)){
                roller.setSpeed(-1);
            }           
            
            //stop by default
            if (!(lStick.getState(C.ROLLERUPBTN) || lStick.getState(C.ROLLERDOWNBTN) || rStick.getState(C.ARMUPBTN) || rStick.getState(C.ARMDOWNBTN))){
                roller.setSpeed(0);
            }
            
            
            //apl other way of dealing with ball shooter
            //if both triggers are pushed.. shoot
           // int LOAD = -1;
            //int READY = 0;
            //int 
               
            //end of other way apl
          
            
            //shoot on trigger
            if (rStick.getState(C.SHOOTERBTN) && lStick.getState(C.SHOOTERBTN)){
                shooter.setSpeed(1);
                loadingForward=true;
                
            //go back once shot
            } else if (loadingForward){
                shooter.setSpeed(-1);
                
                //reverse once trigger back all the way
                if (shooterfront.get()){
                    shooter.setSpeed(1);
                    loadingForward=false;
                    loadingBackward=true;
                }
                
            //stop once in shooting position
            } else if (loadingBackward){
                shooter.setSpeed(1);
                if (shooterback.get()){
                    loadingBackward=false;
                }
            } else {
                shooter.setSpeed(0);
            }
            
            //let button 4 stop slide
            if (rStick.getState(C.SHOOTERSTOPBTN)){
                loadingForward=false;
                loadingBackward=false;
                shooter.setSpeed(0);
            }
            
            //pause for default step time
            Time.step();
            
            //feed watchdog
            Watchdog.getInstance().feed();
        }
    }
    
    //run when test is run
    public void test(){
        message.print(0, "I'm sorry Dave,");
        message.print(1, "I'm afraid");
        message.print(2, "I can't do that.");
    }
    
    //run when disabled
    public void disabled(){
        Time.step();
        message.clear("Disabled");
    }
}