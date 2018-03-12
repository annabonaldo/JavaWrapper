package Application.TestController;

import Application.Database.DatabaseManager;
import Application.Settings;
import jdk.internal.cmm.SystemResourcePressureImpl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToolController
 {
     static  Process _Scratch;
     static  Process _MouseMonitor;
     static  Process _DesktopRecorderCMD;


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

         String command1 = "ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                         "-probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                         "-preset ultrafast" +
                         " -tune zerolatency -crf 25 -pix_fmt yuv420p \"video output.mp4\"";

         _DesktopRecorderCMD = CmdController.startCmd();
         CmdController.cmdWrite(_DesktopRecorderCMD, command);
     }
     static void StartMouseMonitorProcess() throws IOException {
        String exe = Settings.MOUSEMONITOR_EXE;
        String min = "min:";
        String sec = "sec:";

        _MouseMonitor = CmdController.start(exe, min, sec);
    }

     static void EndMouseMonitorProcess(Boolean saveData) throws IOException {
        CmdController.stop(_MouseMonitor);
        if(saveData) CollectMouseMonitorResults();
    }


     static void EndSreenRecorderProcess(Boolean saveData) throws IOException {
        String command = "q";
        CmdController.cmdWrite(_DesktopRecorderCMD, command);
        CmdController.stop(_DesktopRecorderCMD);
     }


     private static void CollectMouseMonitorResults() {

     }


     public static void StartSession() {

        // DatabaseManager.RefreshReportDir();
         try {
           //  StartScratchProcess();
          //   StartMouseMonitorProcess();
             StartScreenRecorderProcess();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public static void EndSession() {

         try {
             TakeSolutionScreenshot();
             EndMouseMonitorProcess(true);
             EndSreenRecorderProcess(true);
         } catch (IOException e) {
             e.printStackTrace();
         } catch (AWTException e) {
             e.printStackTrace();
         }

     }

     private static void TakeSolutionScreenshot() throws AWTException, IOException {
         File reportDir =  DatabaseManager.ReportDir();
         File screenShot = new File(reportDir+Settings.SEP+Settings.SCREENSHOTFILE);
         BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
         ImageIO.write(image, "png", screenShot);
     }
 }
