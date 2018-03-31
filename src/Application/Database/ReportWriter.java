package Application.Database;

import Application.Settings;
import Application.TestController.ExecutionTimerTool;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;

/**
 * Created by Anna Bonaldo on 14/03/2018.
 */
public class ReportWriter {

    public enum REPORT {
        REPORT_SCREENREC,
        REPORT_TXT,
        REPORT_MOUSEMONITOR,
        REPORT_SOLUTION_SCREENSHOT,
    }

    static File reportDir;

    public  static void CreateReportFolder()
    {
        if (reportDir == null) {
            String path = Settings.REPORT+
                    Settings.SEP + DatabaseManager.ClassID()+
                    Settings.SEP + DatabaseManager.ReportId();
            reportDir = new File(path);
            reportDir.mkdirs();
        }

    }

    public static void WriteReport()
    {
        try {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(GetReportPath(REPORT.REPORT_TXT)), "utf-8"));
            writer.write("ID Studente ;"+ DatabaseManager.studentId+" ;\n");
            writer.write("Classe  ;"+ DatabaseManager.schoolClass._classID+";\n");
            writer.write("Scuola  ;"+ Settings.SCHOOLID+";\n");
            writer.write("Gruppo Progetti ;"+new File(DatabaseManager.testProject.getParent()).getName() +";\n");
            writer.write("Progetto ;"+DatabaseManager.testProject.getName() +";\n");

            writer.write("Data;"        + LocalDate.now().toString()+";\n");
            writer.write("Ora Inizio ;" + ExecutionTimerTool.getStartTime()+";\n");
            writer.write("Ora Fine ;" + ExecutionTimerTool.getStopTime()+";\n");
            writer.write("Durata ;" + ExecutionTimerTool.getExcutionTime().toString()+";\n");
            ArrayList<String> studentData = DBSchoolClass.getStudentDataAt(DatabaseManager.StudentId());
            if(studentData!= null && (!studentData.isEmpty()))
            {
                for (String data:studentData) {
                    writer.write(data);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File GetReportPath(REPORT pathType) {
        if(reportDir == null)
        {   System.err.println("Database setup error ");
        return null;}
         else {
            File reportDestination = reportDir;
            switch (pathType) {
                case REPORT_MOUSEMONITOR: {
                    reportDestination = new File (reportDir.getAbsolutePath()+Settings.SEP + Settings.MOUSEMONITTOR_REPORTDIR);
                    break;
                }
                case REPORT_SCREENREC: {
                    reportDestination = new File(reportDir.getAbsolutePath()+Settings.SEP+Settings.SCREENREC_REPORT_FILE);
                    break;
                }
                case REPORT_TXT: {
                    reportDestination = new File(reportDir.getAbsolutePath()+
                            Settings.SEP+DatabaseManager.getReportTxtFilename());
                    break;
                }
                case REPORT_SOLUTION_SCREENSHOT: {
                    reportDestination = new File(reportDir.getAbsolutePath());
                    break;
                }
            }
            return reportDestination;
        }

    }

    public static void CollectMouseMonitorResults() {
        File backup_dir = new File(Settings.MOUSEMONITOR_BACKUP_DIR);

        if(backup_dir.exists()) {
            for (File file : backup_dir.listFiles()) {
                if (!file.isDirectory()) {
                    try {
                        if (file.getName().contains(Settings.CSV)) {
                            File newFile = new File(reportDir.getAbsolutePath() + Settings.SEP + Settings.MOUSEMONITTOR_REPORT_FILE);
                            Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                        if (Settings.MOUSEDATA_IMAGES && file.getName().contains(Settings.PNG)) {
                            File newFile = new File(reportDir.getAbsolutePath() + Settings.SEP + file.getName());
                            Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        }
                        if (Settings.MOUSEDATA_REPORTS && file.getName().contains(Settings.TXT)) {
                            File newFile = new File(reportDir.getAbsolutePath() + Settings.SEP + file.getName());
                            Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    public static void CleanMouseMoinitorDirectory() {
        File dir = new File(Settings.MOUSEMONITOR_BACKUP_DIR);
        deleteDirectory(dir);
    }

    public static void TakeSolutionScreenshot()  {
        File reportDir = ReportWriter.GetReportPath(ReportWriter.REPORT.REPORT_SOLUTION_SCREENSHOT);
        File screenShot = new File(reportDir+Settings.SEP+Settings.SCREENSHOTFILE);
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", screenShot);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    File file = files[i];
                    try {
                        Files.delete(file.toPath());
                    } catch (NoSuchFileException x) {
                        System.err.format("%s: no such" + " file or directory%n", path);
                    } catch (DirectoryNotEmptyException x) {
                        System.err.format("%s not empty%n", path);
                    } catch (IOException x) {
                        // File permission problems are caught here.
                        System.err.println(x);
                    }
                }
            }
        }
        return (path.delete());
    }


}
