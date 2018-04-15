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
 * Manage information loading from set database folder.
 */
public class DatabaseManager {

    static DBSchoolClass schoolClass;
    static int  studentId = -1;
    static File testProject;// = new File("C:\\Users\\Anna Bonaldo\\Documents\\ScratchTests\\Database\\DatabaseProgetti\\test1\\01.sb2");


    /**
     * Class id for current class. It works only after execution configuration.
     * @return Current Class ID.
     */
    static public String ClassID() {
        return schoolClass._classID;
    }

    /**
     * Student class list  current class. It works only after execution configuration.
     * @return Current student list.
     */
    static public ArrayList<String> StudentsList() {
        return schoolClass.Students();
    }
    /**
     * Set actual class folder.
     */
    static public void SetClass(String classFolder) {
        DatabaseManager.schoolClass = new DBSchoolClass(classFolder);
        DatabaseManager.studentId = -1;
    }

    /**
     * Set current execution project.
     * @param testFolder Project folder.
     * @param project Project name.
     */
    static public void SetProject(String testFolder, String project) {
        ResetConfig();
        String fullPath = Settings.DATABASE_PROJECTS + Settings.SEP + testFolder + Settings.SEP + project;
        DatabaseManager.testProject = new File(fullPath);
    }

    /**
     * Set current student id in execution database data.
     * @param id Student id.
     */
    static public void SetStudentId(int id) {
        DatabaseManager.studentId = id;
    }

    /**
     * @return true if school class has been set.
     */
    public static boolean HasClass() {
        return (schoolClass != null) && (schoolClass._classID != null);
    }

    /**
     * @return true if student id has been set.
     */
    public static boolean HasStudent() {
        return (studentId >= 0);
    }

    /**
     * @return true if execution project has been set.
     */
    public static boolean HasProject() {
        return (testProject != null) && (testProject.exists());
    }

    /**
     *
     * @return Current execution project file.
     */
    public static File Project() {
        return testProject;
    }

    /**
     *
     * @return current class id.
     */

    public static String Class() {
        return schoolClass._classID;
    }

    /**
     *
     * @return Current student id.
     */
    public static Integer StudentId() {
        return studentId;
    }

    /**
     *
     * @return report file name identifier.
     */
    public static String ReportId() {
        String testDir = new File (DatabaseManager.testProject.getParent()).getName();
        String id = "studente"+StudentId() +"_prog" +testDir+ Project().getName().replace(Settings.SCRATCH_EXT, "") +
                "_data" + LocalDate.now().toString();
        while(id.contains(".")) id=id.replace(".", "");
        while(id.contains(":")) id=id.replace(":", "");
        while(id.contains("-")) id=id.replace("-", "");
        return id;
    }

    /**
     * Resets configurations.
     */
    public static void ResetConfig() {
        studentId = -1;
        schoolClass = null;
        testProject = null;
    }

    /**
     * Confirm actual configuration.
     */
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

    /**
     *
     * @return Actual execution report filename.
     */

    public static String getReportFilename() {
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

