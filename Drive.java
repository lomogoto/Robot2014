package edu.wpi.first.wpilibj.templates;

public class Drive {
    //use for drive train
    private Motor l;
    private Motor r;
    
    //construct object with ports of motors
    public Drive(int lPort, int rPort){
        l=new Motor(lPort);
        r=new Motor(rPort);
    }
    
    //construct with motor controller type specification
    public Drive(int lPort, int rPort, boolean type){
        l=new Motor(lPort, type);
        r=new Motor(rPort, type);
    }
    
    //set speed ramped
    public void set(double lSpeed, double rSpeed){
        l.setSpeed(F.ramp(lSpeed));
        r.setSpeed(F.ramp(-1*rSpeed));
    }
    
    //set speed withour ramping
    public void setDirect(double lSpeed, double rSpeed){
        l.setSpeed(lSpeed);
        r.setSpeed(-1*rSpeed);
    }
}
