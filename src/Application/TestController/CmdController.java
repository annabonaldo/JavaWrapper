package Application.TestController;

import Application.Settings;

import java.io.*;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;

public class CmdController {
    static final String CMD = "cmd.exe";
    public static Boolean redirectCmdOut = false;

    public static Process startCmd() throws IOException {
        return start(CMD);
    }

    public static Process start(String processExe) throws IOException {
        Process process = null;
        if (!processExe.isEmpty()) {
            ProcessBuilder builder = new ProcessBuilder(processExe);
            builder.redirectErrorStream(redirectCmdOut);
            process = builder.start();
        }
        return process;
    }

    public static Process start(String processExe, String param1, String param2) throws IOException {
        Process process = null;
        if (!processExe.isEmpty()) {
            ProcessBuilder builder = new ProcessBuilder(processExe, param1, param2);
            builder.redirectErrorStream(redirectCmdOut);
            process = builder.start();
        }
        return process;
    }

    public static void stop(Process process, Boolean forceEnd) throws IOException {
        if (process != null) {
            if (process.isAlive()) process.destroy();
        }
        if (forceEnd) {
            while (process.isAlive())
                process.destroyForcibly();
        }
    }

    public static void cmdWrite(Process cmd, String text) throws IOException {
        if (cmd != null) {
            BufferedWriter p_stdin =
                    new BufferedWriter(new OutputStreamWriter(cmd.getOutputStream()));

            p_stdin.write(text);
            p_stdin.newLine();
            p_stdin.flush();

            if (redirectCmdOut) {
                Scanner s = new Scanner(cmd.getInputStream());
                while (s.hasNext()) {
                    System.out.println(s.next());
                }
                s.close();
            }
        }
    }

    public static void waitOnClose(String command) {
        try {
            if (!command.isEmpty())
                CmdController.cmdWrite(CmdController.startCmd(), command);
            Boolean wait = false;
            String line;
            Process p = Runtime.getRuntime().exec
                    (System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (( line = input.readLine() ) != null) {
                //<-- Parse data here.
                if (line.contains(Settings.MOUSEMONITOR_EXE)) {
                    System.out.println("WAIT: " + line);
                    wait = true;
                }
            }
            input.close();
            if (wait) waitOnClose(command);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }


}