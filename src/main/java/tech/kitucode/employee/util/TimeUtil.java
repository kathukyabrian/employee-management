package tech.kitucode.employee.util;

public class TimeUtil {
    public static String convertMinutesToHourString(long minutes){
        long hours = minutes / 60;
        long minute = minutes % 60;

        return hours + " hours and " + minute + " minutes";
    }
}
