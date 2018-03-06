package Application;

import java.io.*;

/**
 * Created by Anna Bonaldo on 15/01/2018.
 */
public class Settings {

    public static int get_frameRate() {
        return _frameRate;
    }

    public static int get_height() {
        return _height;
    }

    public static int get_width() {
        return _width;
    }



    static String SCHOOL_DATA_DIR;
    public static String DESKTOP_REC_FILE;

    static int _width;
    static int _height;
    static int _frameRate;

    static final String WITDTH     = "W";
    static final String HEIGHT     = "H";
    static final String FRAME_RATE = "FR";

    public static final String SEP = "\\";

    // TODO parametric in Settings file
    public static final String BASEPATH =
            "C:\\Users\\Anna Bonaldo\\Documents\\UsageStats\\ScratchTests";
    public static final String DATABASE = "Database";
    public static final String REPORT = "Report";
    public static final String SETTINGS_FILE = "Settings.txt";
    public static final String IMG_FOLDER = "Imagesdir";
    public static final String CSV_CLASS_FILE = ".csv";



    // external program resources
    //TODO  make them parametric in Settings.txt file
    public static final String  SCRATCH_EXELINK      = "C:\\Program Files (x86)\\Scratch 2\\Scratch 2.exe ";
    public static final String  MOUSEMONITOR_EXELINK = "C:\\Program Files (x86)\\objo\\UsageStats\\UsageStats.exe";
    public static final String  SCRATCH_PROGRAM      = "Scratch 2.exe";
    public static final String  MOUSEMONITOR_PROGRAM = "UsageStats.exe";

    public static String  getStudentsListFile(String classID)
    {
        return DBPath()+SEP+classID+SEP+classID+".csv";
    }

    public static long getMOUSEStatsWindows() {
        return MOUSEStatsWindows;
    }

    static final long  MOUSEStatsWindows  = 20;

    Settings()
    {
    }


    public static String ReportPath()
    {
        return Settings.BASEPATH + Settings.SEP +
                Settings.REPORT  + Settings.SEP+
                DatabaseManager.ClassID();
    }

    public static String ClassPath()
    {
        return Settings.BASEPATH + Settings.SEP +
               Settings.DATABASE + Settings.SEP +
               DatabaseManager.ClassID();
    }

    public static String DBPath()
    {
        return Settings.BASEPATH + Settings.SEP +
                Settings.DATABASE ;
    }

    public static void readSettings()
    {
        try {
            String filename = Settings.BASEPATH+SEP+DATABASE+SEP+SETTINGS_FILE;
           System.out.println("FILE : "+filename);

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                ParseLine(line);
                System.out.println("parsing line: "+line);
                line = br.readLine();
            }
            System.out.println("end parsing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ParseLine(String line)
    {
        if(line.contains(Settings.WITDTH)) {
            line = line.replace(Settings.WITDTH, "");
            Settings._width = Integer.parseInt(get_CleanValue(line));
        }
        else if(line.contains(Settings.HEIGHT)) {
            line = line.replace(Settings.HEIGHT, "");
            Settings._height = Integer.parseInt(get_CleanValue(line));
        }
        else if(line.contains(Settings.FRAME_RATE)) {
            line = line.replace(Settings.FRAME_RATE, "");
            Settings._frameRate = Integer.parseInt(get_CleanValue(line));
        }
    }

    private static String  get_CleanValue(String line)
    {
        String s = line;
        s = s.substring(s.indexOf("=") + 1);
        s = s.substring(0, s.indexOf(";"));
        while(s.contains(" ")) s = s.replace(" ", "");
      //  System.out.println("+++"+s+"+++");
        return s;
    }



}
