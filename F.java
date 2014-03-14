package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.MathUtils;

public class F {
    //ramp -1 to 1 with 1/3 power
    public static double ramp(double x){
        return Math.abs(x)/x*MathUtils.pow(Math.abs(x), 1/3.0);
    }
    
    //make default scale from -1,1 to 0,1
    public static double scale(double x){
        double start = -1;
        double end = 1;
        double nStart = 0;
        double nEnd = 1;
        return (x-start)/(end-start)*(nEnd-nStart)+nStart;
    }
    
    //give value, original range, and new (n) range
    public static double scale(double x,double start, double end, double nStart, double nEnd){
        return (x-start)/(end-start)*(nEnd-nStart)+nStart;
    }
}
