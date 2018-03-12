package Application.Database;

import Application.Settings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Anna Bonaldo on 23/02/2018.
 */
public class DBSchoolClass {
    String _classID;
    ArrayList<String> students = new ArrayList<>();

    DBSchoolClass(String classLine){
        _classID = classLine;
        ReadClass();
        System.out.println(" End ReadClass ");
    }

    public ArrayList<String> Students(){ return students; }

    public void ReadClass()
    {
        System.out.println("getStudentsListFile ");
        try {
            String filename = Settings.getStudentsListFile(this._classID);
            System.out.println(filename);

            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);


            String line = br.readLine();
            while (line != null) {

                String[] lineData = line.split(";");
                System.out.println(lineData[0] +" " +lineData[1]);
                students.add(lineData[1]);
                line = br.readLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}