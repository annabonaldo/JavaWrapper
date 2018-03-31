package Application.Database;

import Application.Settings;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Anna Bonaldo on 22/02/2018.
 */
public class DatabaseManager {

    static DBSchoolClass schoolClass;
    static int  studentId = -1;
    static File testProject;// = new File("C:\\Users\\Anna Bonaldo\\Documents\\ScratchTests\\Database\\DatabaseProgetti\\test1\\01.sb2");


    static public String ClassID() {
        return schoolClass._classID;
    }

    static public ArrayList<String> StudentsList() {
        return schoolClass.Students();
    }

    static public void SetClass(String classFolder) {
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

    public static File ReportDir(ReportWriter.REPORT report) {
        return ReportWriter.GetReportPath(report);
    }

    public static String ReportId() {
        String testDir = new File (DatabaseManager.testProject.getParent()).getName();
        String id = "studente"+StudentId() +"_prog" +testDir+ Project().getName().replace(Settings.SCRATCH_EXT, "") +
                "_data" + LocalDate.now().toString();
        while(id.contains(".")) id=id.replace(".", "");
        while(id.contains(":")) id=id.replace(":", "");
        while(id.contains("-")) id=id.replace("-", "");
        return id;
    }

    public static void ResetConfig() {
        studentId = -1;
        schoolClass = null;
        testProject = null;
    }

    public static void ConfirmConfiguration(){
        if(HasProject() && HasStudent() && HasClass()) {
            ReportWriter.CreateReportFolder();
        }
        else {
            System.err.println("Project == " + testProject);
            System.err.println("Class == " + schoolClass._classID);
            System.err.println("Student == " + studentId);
        }

    }


    public static String getReportTxtFilename() {
        File parent = new File(testProject.getParent());
        String filename=  "REPORT_PROGETTO"+parent.getName()+testProject.getName()+"DATA"+ LocalDate.now().toString();
        filename = filename.replace(Settings.SCRATCH_EXT, "");
        filename=  filename.replace(".", "");
        filename = filename +Settings.CSV;
        int count = 0;
        while(new File(filename).exists())
        {
            count++;
            filename= filename+count;
        }
        return  filename;
    }
}

