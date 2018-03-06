package Application;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Anna Bonaldo on 22/02/2018.
 */
public class DatabaseManager {

    static SchoolClass schoolClass;
    static int studentId = -1;


    static public String ClassID(){ return schoolClass._classID; }
    static public ArrayList<String> StudentsList(String classId)
    {


        return schoolClass.Students();
    }

    static  public void SetClass(String classFolder)
    {
      DatabaseManager.schoolClass = new SchoolClass(classFolder);
    }
    static public void SetStudentId(int id){DatabaseManager.studentId =id; }
    public static boolean ParametersOk(){
        return (schoolClass != null) &&
                (schoolClass._classID != null) &&
                (schoolClass.Students()!= null) &&
                (studentId > 0);
    }
}
