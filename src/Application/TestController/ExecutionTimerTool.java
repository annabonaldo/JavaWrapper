package Application.TestController;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anna Bonaldo on 14/03/2018.
 */
public class ExecutionTimerTool {
    static Instant start;
    static Instant end;
    static String startTime;
    static String endTime;

    public static void  Start()
    {
        end = null;
        endTime = null;
        start = Instant.now();
        startTime = new SimpleDateFormat("HH.mm.ss").format(new Date());
    }
    public static void  Stop() {
        endTime= new SimpleDateFormat("HH.mm.ss").format(new Date());
        end = Instant.now(); }

    public static String getStartTime()
    {
        return startTime;
    }
    public static String getStopTime()  { return  endTime; }

    public static Duration getExcutionTime(){
        return Duration.between(start, end);
    }

}
