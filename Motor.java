package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Motor {
    private Talon talon;
    private Victor victor;
    private boolean v=false;//true if motor controller is a victor
    
    //make talon
    public Motor(int port){
        talon= new Talon(port);
    }
    
    //make controllor
    //send true for a victor or C.VICTOR
    public Motor(int port, boolean v){
        this.v=v;
        if(v){
            this.victor=new Victor(port);
        } else {
            talon= new Talon(port);
        }
    }
    
    //set speed of motor, -1 to 1
    public void setSpeed(double speed){
        if(v){
            victor.set(speed);
        } else {
            talon.set(speed);
        }
    }
}
