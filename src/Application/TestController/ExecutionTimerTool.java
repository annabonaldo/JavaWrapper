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

    /**
     * Starts execution timer.
     */
    public static void  Start()
    {
        end = null;
        endTime = null;
        start = Instant.now();
        startTime = new SimpleDateFormat("HH.mm.ss").format(new Date());
    }
    /**
     * Stops execution timer.
     */
    public static void  Stop() {
        endTime= new SimpleDateFormat("HH.mm.ss").format(new Date());
        end = Instant.now(); }


    /**
     * To get start execution timestamp.
     * @return Start execution timestamp.
     */
    public static String getStartTime()
    {
        return startTime;
    }

    /**
     * To get stop execution timestamp.
     * @return Stop execution timestamp.
     */
    public static String getStopTime()  { return  endTime; }

    /**
     * To get execution duration.
     * @return Execution duration.
     */
    public static Duration getExcutionTime(){
        return Duration.between(start, end);
    }

}
