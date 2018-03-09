package TestMonitor;

import Application.Settings;

import java.io.IOException;

public class ProcessController
 {
     static  Process _Scratch;
     static  Process _MouseMonitor;
     static  Process _DesktopRecorderCMD;

    public static void StartScratchProcess(String project) throws IOException {
        String exe = Settings.SCRATCH_EXE;
        _Scratch = CmdController.start(exe);
    }

    public static void StartScreenRecorderProcess() throws IOException {
         String exe = Settings.SCREENREC_EXE;
         String command = Settings.SCREENREC_EXE+" -y -rtbufsize"+
                         Settings.SCREENREC_BUFFERSIZE +"M -f gdigrab -framerate " +
                         Settings.SCREENREC_FR+
                         " -probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                         "-preset ultrafast" +
                         " -tune zerolatency -crf 25 -pix_fmt yuv420p \""+
                         Settings.SCREENREC_OUTPATH+"\"";

         _DesktopRecorderCMD = CmdController.startCmd();
         CmdController.cmdWrite(_DesktopRecorderCMD, command);
     }

    public static void StartMouseMonitorProcess() throws IOException {
        String exe = Settings.MOUSEMONITOR_EXE;
        String min = "min:";
        String sec = "sec:";

        _MouseMonitor = CmdController.start(exe, min, sec);
    }


    public static void EndScratchProcess() throws IOException {
        CmdController.stop(_Scratch);
    }

    public static void EndMouseMonitorProcess() throws IOException {
        CmdController.stop(_MouseMonitor);
    }

    public static void EndSreenRecorderProcess() throws IOException {
        String command = "q";
        CmdController.cmdWrite(_DesktopRecorderCMD, command);
        CmdController.stop(_DesktopRecorderCMD);

     }

}
