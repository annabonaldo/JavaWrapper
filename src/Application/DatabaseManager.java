package Application;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Anna Bonaldo on 22/02/2018.
 */
public class DatabaseManager {

    static SchoolClass schoolClass;
    static int studentId = -1;
    static File testProject;


    static public String ClassID(){ return schoolClass._classID; }
    static public ArrayList<String> StudentsList()
    {
        return schoolClass.Students();
    }

    static  public void SetClass(String classFolder)
    {
        System.out.println("classFolder "+classFolder);
        DatabaseManager.schoolClass = new SchoolClass(classFolder);
        DatabaseManager.studentId = -1;
    }

    static  public void SetProject(String testFolder, String project)
    {
        String fullPath =Settings.DATABASE_PROJECTS + Settings.SEP + testFolder + Settings.SEP + project;
        DatabaseManager.testProject = new File(fullPath);
        if(testProject.exists()){
            System.out.println("Path progetto settato correttamente");
        }
    }

    static public void SetStudentId(int id){DatabaseManager.studentId =id; }

    public static boolean HasClass() {
        return (schoolClass != null) && (schoolClass._classID != null);
    }

    public static boolean HasStudent() {
        return (studentId >= 0);
    }

    public static boolean HasProject() {return (testProject != null) && (testProject.exists()); }
}
