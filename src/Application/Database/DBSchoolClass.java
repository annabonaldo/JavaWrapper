package Application.Database;

import Application.Settings;
import Application.TestController.ExecutionTimerTool;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class manages class information loading from database. During execution it storages
 * information on actual class and their students.
 */
public class DBSchoolClass {
    static String dataFileHeader= "ID;GENERE;ETA;NAZIONALITA;LIVELLO ITALIANO;";
    static private   ArrayList<String> classData = new ArrayList<>();
    String _classID;
    ArrayList<String> students = new ArrayList<>();

    DBSchoolClass(String classLine){
        _classID = classLine;
        ReadClass();
        ReadStudentsData();
    }

    /**
     * @return Current students list.
     */
    public ArrayList<String> Students(){ return students; }

    /**
     * Reads current class data.
     */
    public void ReadClass()
    {
        try {
            String filename = Settings.getStudentsListFile(this._classID);

            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            String line = br.readLine();
            while (line != null) {
                String[] lineData = line.split(";");
                students.add(lineData[1]);
                line = br.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads current student data.
     */
    public void ReadStudentsData() {
        try {
            String filename = Settings.getClassDataFile(this._classID);
            FileReader fileReader = new FileReader(filename);
            if(new File(filename).exists()) {
                BufferedReader br = new BufferedReader(fileReader);
                String line = br.readLine();
                line= br.readLine();// skip header line
               while(line != null)  {
                   classData.add(line);
                   line = br.readLine();
               }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
           // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getStudentDataAt(int i){
        ArrayList<String> out = new ArrayList<>();
        if(classData.size() > i) {

            String[] data = classData.get(i).split(";");
            String[] header = DBSchoolClass.dataFileHeader.split(";");
            for(int d=0; d<data.length; d++){
              try{
                  String h = ";";
                  if(header.length > d) h = header[d]+h;
                  out.add(h+data[d]+";\n");
                  }
              catch (Exception e){
                  e.printStackTrace();
                  System.err.println("error in class data file ");
              }
            }
        }
        return out;
    }
}
