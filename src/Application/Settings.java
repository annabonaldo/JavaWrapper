package Application;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.tools.JavaFileManager;
import java.io.*;
import java.nio.file.*;

/**
 * Created by Anna Bonaldo on 15/01/2018.
 */
public class Settings {

    public static final String SEP = "\\";
    public static final String CSV = ".csv";
    public static final String EXE = ".exe";
    static  final String  SCREENREC_OUTFILE         = "screenRec.mp4";
    static  final String  MOUSEMONITTOR_OUTFILE     = "mouseStats.csv";


    public static final String ROOT_DIR = FileSystemView.getFileSystemView().getDefaultDirectory()+SEP+"ScratchTests";
    public static final String DATABASE_CLASSES  = ROOT_DIR+SEP+"Database\\DatabaseClassi";
    public static final String DATABASE_PROJECTS = ROOT_DIR+SEP+"Database\\DatabaseProgetti";
    public static final String DATABASE          = ROOT_DIR+SEP+"Database";
    public static final String REPORT            = ROOT_DIR+SEP+"Report";
    public static final String SETTINGS_FILE     = ROOT_DIR+SEP+"Settings.txt";



    public static  String  SCRATCH_EXE      = "Scratch 2"; //Default value
    public static  String  MOUSEMONITOR_EXE = "UsageStats";//Default value
    public static  String  SCREENREC_EXE    = "ffmpeg";    //Default value


    public static  String  SCRATCH_FULLPATH;
    public static  String  MOUSEMONITOR_FULLPATH;
    public static  String  SCREENREC_FULLPATH;
    public static String   MOUSEMONITOR_BACKUP_DIR =
            FileSystemView.getFileSystemView().getDefaultDirectory()+SEP+"UsageStats";        // "C:\\Users\\Anna Bonaldo\\Documents\\UsageStats\\";

    //todo create get methods
    public  static  String  SCREENREC_OUTPATH;
    public  static  String  MOUSEMONITTOR_OUTPATH;

    public static  int  MOUSEMONITTOR_MIN_TIMESPAN = 10;//Default value
    public static  int  MOUSEMONITTOR_SEC_TIMESPAN = 0; //Default value

    public static  int  SCREENREC_FR = 30 ;
    public static  int  SCREENREC_BUFFERSIZE = 100;
    // end todo ------------



    static final String  TXT_SCRATCH_EXE_FULLPATH      = "SCRATCH_EXE_PATH";
    static final String  TXT_MOUSEMONITOR_EXE_FULLPATH = "MOUSEMONITOR_EXE_PATH";
    static final String  TXT_SCREENREC_EXE_FULLPATH    = "SCREENREC_EXE_PATH";
    static final String  TXT_MOUSEMONITOR_BACKUP_DIR   = "MOUSEMONITOR_BACKUP_DIR";

    static final String  TXT_MOUSEMONITTOR_MIN_TIMESPAN = "MOUSEMONITTOR_MIN_TIMESPAN";
    static final String  TXT_MOUSEMONITTOR_SEC_TIMESPAN = "MOUSEMONITTOR_SEC_TIMESPAN";

    static final String  TXT_SCREENREC_FR= "SCREENREC_FR";
    static final String  TXT_SCREENREC_BUFFERSIZE = "SCREENREC_BUFFERSIZE";




    public static String  getStudentsListFile(String classID)
    {
        System.out.println("CSV: "+ Path(DATABASE_CLASSES, classID)+SEP+classID+CSV);
       return Path(DATABASE_CLASSES, classID)+SEP+classID+CSV;
    }

    public static String ClassPath(String dir, String file)
    {
        return ClassPath(dir)+SEP+file;
    }

    public static String ClassPath(String dir)
    {
        return dir+SEP+DatabaseManager.ClassID();
    }

    public static String Path(String dir, String file )
    {
        return dir+SEP+file;
    }

    public static void readSettings()
    {
       try {

        File root = new File(ROOT_DIR);                   if(!root.exists()) root.mkdirs();
        File database = new File(DATABASE);               if(!database.exists()) database.mkdirs();
        File databaseClassi = new File(DATABASE_CLASSES); if(!databaseClassi.exists()) databaseClassi.mkdirs();
        File databasePrj = new File(DATABASE_PROJECTS);   if(!databasePrj.exists()) databasePrj.mkdirs();
        File reports= new File(REPORT);                   if(!reports.exists()) reports.mkdirs();
        File settings= new File(SETTINGS_FILE);           if(!settings.exists())  settings.createNewFile();

        BufferedReader br= new BufferedReader(new FileReader(SETTINGS_FILE));
        String line = br.readLine();
        while (line != null) {
                ParseLine(line);
                System.out.println("parsing line: "+line);
                line = br.readLine();
        }
        System.out.println("end parsing");
       }
       catch (FileNotFoundException e) {
            e.printStackTrace();
       }
       catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ParseLine(String line)
    {
        // PATH PARAMETERS PARSING
        if(line.contains(Settings.TXT_SCRATCH_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_SCRATCH_EXE_FULLPATH, "");
            parseExePath(line, SCRATCH_EXE, SCRATCH_FULLPATH);

        }
        else if(line.contains(Settings.TXT_MOUSEMONITOR_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_MOUSEMONITOR_EXE_FULLPATH, "");
            parseExePath(line, MOUSEMONITOR_EXE, MOUSEMONITOR_FULLPATH);

        }
        else if(line.contains(Settings.TXT_SCREENREC_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_SCREENREC_EXE_FULLPATH, "");
            parseExePath(line, SCREENREC_EXE, SCREENREC_FULLPATH);
        }
        else if(line.contains(Settings.TXT_MOUSEMONITOR_BACKUP_DIR)) {
            line = line.replace(Settings.TXT_MOUSEMONITOR_BACKUP_DIR, "");
            parsePath(line, MOUSEMONITOR_BACKUP_DIR);
        }

        else if(line.contains(Settings.TXT_SCREENREC_BUFFERSIZE)) {
            line = line.replace(Settings.TXT_SCREENREC_BUFFERSIZE, "");
            Settings.SCREENREC_BUFFERSIZE = Integer.parseInt(get_CleanValue(line));
        }
        // NUMERIC PARAMETERS PARSING
        else if(line.contains(Settings.TXT_SCREENREC_FR)) {
            line = line.replace(Settings.TXT_SCREENREC_FR, "");
            Settings.SCREENREC_FR = Integer.parseInt(get_CleanValue(line));
        }
        else if(line.contains(Settings.TXT_MOUSEMONITTOR_MIN_TIMESPAN)) {
            line = line.replace(Settings.TXT_MOUSEMONITTOR_MIN_TIMESPAN, "");
            Settings.MOUSEMONITTOR_MIN_TIMESPAN = Integer.parseInt(get_CleanValue(line));
        }
        else if(line.contains(Settings.TXT_MOUSEMONITTOR_SEC_TIMESPAN)) {
            line = line.replace(Settings.TXT_MOUSEMONITTOR_SEC_TIMESPAN, "");
            Settings.MOUSEMONITTOR_SEC_TIMESPAN = Integer.parseInt(get_CleanValue(line));
        }

    }

    private static String  get_CleanValue(String line)
    {
        String s = line;
        s = s.substring(s.indexOf("=") + 1);
        s = s.substring(0, s.indexOf(";"));
        while(s.contains(" ")) s = s.replace(" ", "");
       // System.out.println("+++"+s+"+++");
        return s;
    }

    private static void  parseExePath(String line, String exefilename, String fullpath)
    {
        String s = line;
        s = s.substring(s.indexOf("\"") + 1);
        s = s.substring(0, s.indexOf("\""));
        File file = new File(s);
        exefilename =  file.getName().replace(EXE, "");
        fullpath = file.getAbsolutePath();
        System.out.println("EXE FILENAME: " +exefilename+" | FULLPATH: "+fullpath);
    }
    private static void  parsePath(String line, String path)
    {
        String s = line;
        s = s.substring(s.indexOf("\"") + 1);
        s = s.substring(0, s.indexOf("\""));
        File file = new File(s);
        path = file.getAbsolutePath();
        System.out.println("PATH: " +path);
    }



}
