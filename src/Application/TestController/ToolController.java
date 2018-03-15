package Application.TestController;
import Application.Database.DatabaseManager;
import Application.Database.ReportWriter;
import Application.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;

enum PROCESS {
    SCRATCH_PROCESS,
    SCREENREC_PROCESS,
    MOUSEMONITOR_PROCESS
}
class ToolExecution implements Runnable{

    PROCESS toolProcess;
    ToolExecution(PROCESS process){
        toolProcess = process;
    }

    static void StartProcess(PROCESS process) throws IOException {
        switch (process){
            case SCRATCH_PROCESS: {
                ToolController.StartScratchProcess();
                break;}
            case MOUSEMONITOR_PROCESS: {
                ToolController.StartMouseMonitorProcess();
                break;}
            case SCREENREC_PROCESS: {
                ToolController.StartScreenRecorderProcess();
                break;}
        }
    }

    static void EndProcess(PROCESS process) throws IOException {
        switch (process){
            case SCRATCH_PROCESS: {
                ToolController.EndScratchProcess();
                break;}
            case MOUSEMONITOR_PROCESS: {
                ToolController.EndMouseMonitorProcess();
                break;}
            case SCREENREC_PROCESS: {
                ToolController.EndSreenRecorderProcess();
                break;}
        }
    }

    @Override
    public void run() {
        try {
            ToolExecution.StartProcess(toolProcess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StopToolExecution(){
        try {
          ToolExecution.EndProcess(toolProcess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class ToolController
 {
     static  ToolExecution mousemonitorTool;
     static  ToolExecution scratchTool;
     static  ToolExecution screenrecTool;
     static  Thread mousemonitorExecution;
     static  Thread scratchExecution;
     static  Thread screenrecExecution;
     static  Process _Scratch;
     static  Process _MouseMonitor;
     static  Process _DesktopRecorderCMD;

     public static void Start(){

         scratchTool = new ToolExecution(PROCESS.SCRATCH_PROCESS);
         scratchExecution = new Thread(scratchTool);
         scratchExecution.start();

       if(Settings.MOUSEMONITOR_ACTIVE) {
           mousemonitorTool = new ToolExecution(PROCESS.MOUSEMONITOR_PROCESS);
           mousemonitorExecution = new Thread(mousemonitorTool);
           mousemonitorExecution.start();
       }
         if(Settings.SCREENREC_ACTIVE) {
                screenrecTool = new ToolExecution(PROCESS.SCREENREC_PROCESS);
                screenrecExecution = new Thread(screenrecTool);
               screenrecExecution.start();
         }

         ExecutionTimerTool.Start();
     }
     public static void Stop() {

         ReportWriter.TakeSolutionScreenshot();
         ExecutionTimerTool.Stop();
         ReportWriter.WriteReport();

        scratchTool.StopToolExecution();
         if(Settings.MOUSEMONITOR_ACTIVE)  mousemonitorTool.StopToolExecution();
         if(Settings.SCREENREC_ACTIVE)     screenrecTool.StopToolExecution();

         scratchExecution.interrupt();
         if(Settings.SCREENREC_ACTIVE)   screenrecExecution.interrupt();
         if(Settings.MOUSEMONITOR_ACTIVE)  mousemonitorExecution.interrupt();

    }

     static void StartScratchProcess() throws IOException {
       File project = DatabaseManager.Project();

         if(project.exists()){
          String exe = Settings.SCRATCH_FULLPATH;
          _Scratch = CmdController.start(exe, project.getAbsolutePath(), "");
     }
     }
     static void EndScratchProcess() throws IOException {
             CmdController.stop(_Scratch);

     }
     static void StartScreenRecorderProcess() throws IOException {
         String exe = Settings.SCREENREC_EXE;
         String file = ReportWriter.GetReportPath(ReportWriter.REPORT.REPORT_SCREENREC).getAbsolutePath();

         String command = "ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                         " -probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                         "-preset ultrafast" +
                         " -tune zerolatency -crf 25 -pix_fmt yuv420p \""+file+"\"";

         _DesktopRecorderCMD = CmdController.startCmd();
         CmdController.cmdWrite(_DesktopRecorderCMD, command);
     }
     static void EndSreenRecorderProcess() throws IOException {
         String command = "q";
         CmdController.cmdWrite(_DesktopRecorderCMD, command);
         CmdController.stop(_DesktopRecorderCMD);
     }
     static void StartMouseMonitorProcess() throws IOException {
         ReportWriter.CleanMouseMoinitorDirectory();
         String min = " min:"+Settings.MOUSEMONITTOR_MIN_TIMESPAN;
         String sec = " sec:"+Settings.MOUSEMONITTOR_SEC_TIMESPAN;
         String command = Settings.MOUSEMONITOR_EXE+min+sec;
         _MouseMonitor = CmdController.startCmd();
         CmdController.cmdWrite(_MouseMonitor, command);
     }
     static void EndMouseMonitorProcess()throws IOException {
         String command = "taskkill /IM "+Settings.MOUSEMONITOR_EXE+Settings.EXE+ " /F";
         CmdController.cmdWrite(_MouseMonitor, command);
         CmdController.waitOnClose(command);
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         ReportWriter.CollectMouseMonitorResults();
     }


 }
