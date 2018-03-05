package TestMonitor;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public abstract class Monitor implements Runnable{

    Monitor(){super();}

    AtomicBoolean stop;
    public   void StopExecution() {
        if(!stop.get())
            stop.set(true);
    }
    abstract void CollectData();
    abstract void PreRunOperations();
    abstract void PostRunOperations();


    @Override
    public void run() {
        try {
            PreRunOperations();
            while(!stop.get())
            {
                CollectData();
                Thread.sleep(Settings.getMOUSEStatsWindows()*60*1000);
            }
            PostRunOperations();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
