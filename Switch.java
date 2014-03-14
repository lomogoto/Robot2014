package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

public class Switch {
    private DigitalInput in;
    private boolean normallyOff;
    
    //initialize switch
    public Switch(int port){
        in = new DigitalInput(port);
        normallyOff=false;
    }
    
    public Switch(int port, boolean normallyOff){
        in = new DigitalInput(port);
        this.normallyOff=normallyOff;
    }
    
    //get if triggered or not
    public boolean get(){
        return in.get() == normallyOff;
    }
}
