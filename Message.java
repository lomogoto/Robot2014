package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

public class Message {
    private DriverStationLCD message;
    
    public Message(){
        message=DriverStationLCD.getInstance();
    }
    
    //print text to the driver station at given line
    public void print(int n, Object o){
        //clear out line
        String text=o.toString()+"                      ";
        
        //use different line based off n
        switch(n){
            case 0:
                message.println(DriverStationLCD.Line.kUser1, 1, text);
                break;
            case 1:
                message.println(DriverStationLCD.Line.kUser2, 1, text);
                break;
            case 2:
                message.println(DriverStationLCD.Line.kUser3, 1, text);
                break;
            case 3:
                message.println(DriverStationLCD.Line.kUser4, 1, text);
                break;
            case 4:
                message.println(DriverStationLCD.Line.kUser5, 1, text);
                break;
            case 5:
                message.println(DriverStationLCD.Line.kUser6, 1, text);
                break;
        }
        
        //update the screen
        message.updateLCD();
    }
    
    //print in formated way
    public void debug(int n, String name, Object value){
        print(n,name+" : "+value.toString());
    }
    
    //clear screen
    public void clear(){
        for (int i=0; i<6; i++){
            print(i, "");
        }
    }
    
    //clear with message
    public void clear(String s){
        clear();
        print(0,s);
    }
}
