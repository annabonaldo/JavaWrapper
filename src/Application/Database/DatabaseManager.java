package Application.Database;

import Application.Settings;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Anna Bonaldo on 22/02/2018.
 */
public class DatabaseManager {

    static DBSchoolClass schoolClass;
    static int studentId = -1;
    static File testProject;// = new File("C:\\Users\\Anna Bonaldo\\Documents\\ScratchTests\\Database\\DatabaseProgetti\\test1\\01.sb2");

    static File reportDir;


    static public String ClassID() {
        return schoolClass._classID;
    }

    static public ArrayList<String> StudentsList() {
        return schoolClass.Students();
    }

    static public void SetClass(String classFolder) {
        System.out.println("classFolder " + classFolder);
        DatabaseManager.schoolClass = new DBSchoolClass(classFolder);
        DatabaseManager.studentId = -1;
    }

    static public void SetProject(String testFolder, String project) {
        ResetConfig();
        String fullPath = Settings.DATABASE_PROJECTS + Settings.SEP + testFolder + Settings.SEP + project;
        DatabaseManager.testProject = new File(fullPath);
    }

    static public void SetStudentId(int id) {
        DatabaseManager.studentId = id;
    }

    public static boolean HasClass() {
        return (schoolClass != null) && (schoolClass._classID != null);
    }

    public static boolean HasStudent() {
        return (studentId >= 0);
    }

    public static boolean HasProject() {
        return (testProject != null) && (testProject.exists());
    }

    public static File Project() {
        return testProject;
    }

    public static String Class() {
        return schoolClass._classID;
    }

    public static Integer StudentId() {
        return studentId;
    }

    public static File ReportDir() {
        RefreshReportDir();
        return reportDir;
    }

    public static String ReportId() {
        return Settings.SCHOOLID + ClassID() + StudentId() + Project().getName() + LocalDateTime.now().toString();
    }

    public static void ResetConfig() {
        studentId = -1;
        schoolClass = null;
        testProject = null;
        reportDir = null;
    }

    public static void RefreshReportDir() {
        if (reportDir == null) {
            String path = Settings.ClassPath() + Settings.SEP + ReportId();
            reportDir = new File(path);
            reportDir.mkdirs();
        }
        Settings.SCREENREC_OUTPATH = reportDir.getAbsolutePath()
                +Settings.SEP+Settings.SCREENREC_OUTFILE;
    }
}

