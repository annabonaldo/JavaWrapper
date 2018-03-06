package Application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Anna Bonaldo on 23/02/2018.
 */
public class SchoolClass {
    String _classID;
    ArrayList<String> students = new ArrayList<>();

    SchoolClass(String classLine){
        _classID = classLine;
        ReadClass();
    }

    public ArrayList<String> Students(){ return students; }

    public void ReadClass()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Settings.getStudentsListFile(this._classID)));

            System.out.println(Settings.getStudentsListFile(this._classID));

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
