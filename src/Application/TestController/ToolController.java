package Application.TestController;

import Application.Database.DatabaseManager;
import Application.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        System.out.println("THREAD in ");
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
     static Thread mousemonitorExecution;
     static Thread scratchExecution;
     static Thread screenrecExecution;
     static ToolExecution mousemonitorTool;
     static ToolExecution scratchTool;
     static ToolExecution screenrecTool;


     static  Process _Scratch;
     static  Process _MouseMonitor;
     static  Process _DesktopRecorderCMD;


     public static void Start(){
         DatabaseManager.RefreshReportDir();

         scratchTool = new ToolExecution(PROCESS.SCRATCH_PROCESS);
         scratchExecution = new Thread(scratchTool);
         scratchExecution.start();

       /*  mousemonitorTool = new ToolExecution(PROCESS.MOUSEMONITOR_PROCESS);
         mousemonitorExecution = new Thread(mousemonitorTool);
         mousemonitorExecution.start();*/

         screenrecTool = new ToolExecution(PROCESS.SCREENREC_PROCESS);
         screenrecExecution = new Thread(screenrecTool);
         screenrecExecution.start();


     }

    public static void Stop() {

         try {
             scratchTool.StopToolExecution();
            // mousemonitorTool.StopToolExecution();
             screenrecTool.StopToolExecution();

             scratchExecution.join(2000);
            // mousemonitorExecution.join(2000);
             screenrecExecution.join(2000);

             System.out.println("joined ");
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
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
      //   DatabaseManager.RefreshReportDir();
         String exe = Settings.SCREENREC_EXE;
         String file = Settings.DATABASE+Settings.SEP+Settings.SCREENREC_OUTFILE;

         String command = "ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                         " -probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                         "-preset ultrafast" +
                         " -tune zerolatency -crf 25 -pix_fmt yuv420p \""+file+"\"";
        System.out.println(command);

       /*  String command1 = "ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                         "-probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                         "-preset ultrafast" +
                         " -tune zerolatency -crf 25 -pix_fmt yuv420p \"video output.mp4\"";*/

         _DesktopRecorderCMD = CmdController.startCmd();
         CmdController.cmdWrite(_DesktopRecorderCMD, command);
     }
     static void StartMouseMonitorProcess() throws IOException {
        String exe = Settings.MOUSEMONITOR_EXE;
        String min = "min:";
        String sec = "sec:";

        _MouseMonitor = CmdController.start(exe, min, sec);
    }

     static void EndMouseMonitorProcess() throws IOException {
        CmdController.stop(_MouseMonitor);
        CollectMouseMonitorResults();
    }

     static void EndSreenRecorderProcess() throws IOException {
        String command = "q";
        CmdController.cmdWrite(_DesktopRecorderCMD, command);
        CmdController.stop(_DesktopRecorderCMD);
     }


     private static void CollectMouseMonitorResults() {

     }

     private static void TakeSolutionScreenshot() throws AWTException, IOException {
         File reportDir =  DatabaseManager.ReportDir();
         File screenShot = new File(reportDir+Settings.SEP+Settings.SCREENSHOTFILE);
         BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
         ImageIO.write(image, "png", screenShot);
     }
 }
