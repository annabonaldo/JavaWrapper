package TestMonitor;

import Application.Settings;

import java.io.IOException;

public class ExteralProcessController
 {
     static  Process _Scratch;
     static  Process _MouseMonitor;

    public static void StartScratchProcess(String project) throws IOException {
        ProcessBuilder p = new ProcessBuilder(Settings.SCRATCH_EXELINK);
        _Scratch = p.start();

    }

     public static void StartMouseMonitorProcess() throws IOException {
         ProcessBuilder p = new ProcessBuilder(Settings.MOUSEMONITOR_EXELINK );
         _MouseMonitor = p.start();
     }

    public static void EndScratchProcess() throws IOException {
        _Scratch.destroy();
    }
    public static void EndMouseMonitorProcess() throws IOException {
        _MouseMonitor.destroy();
    }


    private  static void EndProcess(String processName)throws IOException {
       //  String[] cmdArray = new String[]{ExteralProcessController.END_CMD.replace("&", processName)};
     //    Runtime.getRuntime().exec(cmdArray);
    }
}
