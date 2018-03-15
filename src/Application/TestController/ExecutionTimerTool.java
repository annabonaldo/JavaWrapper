package Application.TestController;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;

/**
 * Created by Anna Bonaldo on 14/03/2018.
 */
public class ExecutionTimerTool {
    static Instant start;
    static Instant end;

    public static void  Start()
    {
        end = null;
        start = Instant.now();
    }
    public static void  Stop() { end = Instant.now(); }

    public static Instant getStartInstant()
    {
        return start;
    }
    public static Instant getStopInstant()  { return  end; }

    public static Duration getExcutionTime(){
        return Duration.between(start, end);
    }

}
