package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;

public class Time {
    //pause for default time
    public static void step(){
        Timer.delay(C.REFRESHRATE);
    }
    
    //pause for other time, watchdog still active
    public static void step(double seconds){
        Timer.delay(seconds);
    }
    
    //pause for time with watchdog off (use for long pauses like in autonomous)
    public static void sleep(double seconds){
        Watchdog watch=Watchdog.getInstance();
        watch.setEnabled(false);
        Timer.delay(seconds);
        watch.setEnabled(true);
    }
}
