package TestMonitor;

import java.io.*;
import java.util.Scanner;

public class CmdController {
    static final String CMD = "cmd.exe";

    public static Process startCmd()throws IOException {
        return start(CMD);
    }

    public static Process start(String processExe) throws IOException {
       Process process = null;
        if(!processExe.isEmpty()) {
            ProcessBuilder builder = new ProcessBuilder(processExe);
            builder.redirectErrorStream(true);
            process = builder.start();
        }
        return process;
    }

    public static Process start(String processExe, String param1, String param2) throws IOException {
        Process process = null;
        if(!processExe.isEmpty()) {
            ProcessBuilder builder = new ProcessBuilder(processExe, param1, param2);
            builder.redirectErrorStream(true);
            process = builder.start();
        }
        return process;
    }

    public static void stop(Process process) throws IOException {
       if(process!= null){ if (process.isAlive()) process.destroy();}
    }

    public static void cmdWrite(Process cmd, String text) throws IOException {

        if(cmd != null) {
            BufferedWriter p_stdin =
                    new BufferedWriter(new OutputStreamWriter(cmd.getOutputStream()));

            //single execution
            p_stdin.write(text);
            p_stdin.newLine();
            p_stdin.flush();
            Scanner s = new Scanner(cmd.getInputStream());
            while (s.hasNext()) {
                System.out.println(s.next());
            }
            s.close();
        }
    }


 /*   public void start() throws Exception {
        cmdBuilder = new ProcessBuilder("cmd.exe");
        cmdBuilder.redirectErrorStream(true);

        try {
            cmdProcess = cmdBuilder.start();


        //get stdin of shell
        BufferedWriter p_stdin =
                new BufferedWriter(new OutputStreamWriter(cmdProcess.getOutputStream()));

            //single execution
            p_stdin.write("ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                    "-probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                    "-preset ultrafast" +
                    " -tune zerolatency -crf 25 -pix_fmt yuv420p \"video output.mp4\"");

            p_stdin.newLine();
            p_stdin.flush();
            //  p_stdin.write("dir");
            //   p_stdin.newLine();
            //   p_stdin.flush();

        }

        catch (IOException e) {
            System.out.println(e);
        }


    }
    public void stop() throws IOException {
        BufferedWriter p_stdin =
                new BufferedWriter(new OutputStreamWriter(cmdProcess.getOutputStream()));

        //single execution
        p_stdin.write("q");
        p_stdin.newLine();
        p_stdin.flush();

    }*/
}

    /*   public CmdController() throws Exception {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe");
            builder.redirectErrorStream(true);

            Process p=null;
            try {
                p = builder.start();
            }
            catch (IOException e) {
                System.out.println(e);
            }
            //get stdin of shell
            BufferedWriter p_stdin =
                    new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            // execute the desired command (here: ls) n times
            int n=10;
           // for (int i=0; i<n; i++) {
                try {
                    //single execution
                    p_stdin.write("ffmpeg -y -rtbufsize 100M -f gdigrab -framerate 30 " +
                            "-probesize 10M -draw_mouse 1 -i desktop -c:v libx264 -r 30 " +
                            "-preset ultrafast" +
                            " -tune zerolatency -crf 25 -pix_fmt yuv420p \"video output.mp4\"");

                    p_stdin.newLine();
                    p_stdin.flush();
                  //  p_stdin.write("dir");
                 //   p_stdin.newLine();
                 //   p_stdin.flush();
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            //}

            // finally close the shell by execution exit command
            try {
                p_stdin.write("exit");
                p_stdin.newLine();
                p_stdin.flush();
            }
            catch (IOException e) {
                System.out.println(e);
            }

            // write stdout of shell (=output of all commands)
            Scanner s = new Scanner( p.getInputStream() );
            while (s.hasNext())
            {
                System.out.println( s.next() );
            }
            s.close();
        }
*/

