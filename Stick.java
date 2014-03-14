package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

public class Stick {
    private Joystick stick;
    
    //initiate
    public Stick(int port){
        stick=new Joystick(port);
    }
    
    //get the x axis
    public double getX(){
        return -1*stick.getAxis(Joystick.AxisType.kX);
    }
    
    //get the y axis
    public double getY(){
        return -1*stick.getAxis(Joystick.AxisType.kY);
    }
    
    //get a button's state (pressed or not)
    public boolean getState(int button){
        return stick.getRawButton(button);
    }
    
    //get the throttle level 0,1
    public double getThrottle(){
        return F.scale( -1*stick.getAxis(Joystick.AxisType.kZ));
    }
}
