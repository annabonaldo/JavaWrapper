package TestMonitor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Anna Bonaldo on 15/01/2018.
 */
public class Settings {


    public static String get_ScreenImgsDir() {
        return _ScreenImgsDir;
    }
    public static String get_ScreenMovFile(){
        return _ScreenMovFile;
    }

    public static int get_frameRate() {
        return _frameRate;
    }

    public static int get_height() {
        return _height;
    }

    public static int get_width() {
        return _width;
    }


    static String _ScreenImgsDir;
    static String  _ScreenMovFile;
    static int _width;
    static int _height;
    static int _frameRate;

    public static final String  SettingsFilePath = "";
    static final String _IMGDIR = "IMGDIR";
    static final String _SCREENMOVOUT = "SCREENMOVOUT";



    Settings()
    {
    }

    public static void readSettings()
    {
        try {
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));

           // StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                //sb.append(line);
               // sb.append(System.lineSeparator());
                line = br.readLine();
                ParseLine(line);
            }
            //String everything = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ParseLine(String line)
    {
        if(line.contains(_SCREENMOVOUT))
            Settings._ScreenMovFile = get_CleanValue(line);
        else if(line.contains(_IMGDIR))
            Settings._ScreenImgsDir =get_CleanValue(line);

    }

    private static String  get_CleanValue(String line)
    {
        String s = line;
        s = s.substring(s.indexOf("=") + 1);
        s = s.substring(0, s.indexOf(";"));
        s.replace(" ", "");

        System.out.println(s);
        return s;
    }



}
