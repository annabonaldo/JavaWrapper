package Application;

import Application.Database.DatabaseManager;


import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * This class loads and stores all program settings form "Settings.txt" file.
 */
public class Settings {

    /**
    Utility variable: Path separator.
     **/
    public static final String SEP = "\\";
    /**
     Utility variable: "csv" file extension.
     **/
    public static final String CSV = ".csv";
    /**
     Utility variable: "exe" file extension.
     **/
    public static final String EXE = ".exe";
    /**
     Utility variable: "txt" file extension.
     **/
    public static final String TXT = ".txt";
    /**
     Utility variable: "png" file extension.
     **/
    public static final String PNG = ".png";
    /**
     Utility variable: "sb2" (Scratch project) file extension.
     **/
    public static final String SCRATCH_EXT = ".sb2";
    /**
     Settings variable: screen recorder report file's name.
     **/
    public static  final String  SCREENREC_REPORT_FILE      = "registrazSchermo.mp4";
    /**
     Settings variable: mouse monitor report directory's name.
     **/
    public static  final String  MOUSEMONITTOR_REPORTDIR    = "DatiMouse";
    /**
     Settings variable: mouse monitor report file's name.
     **/
    public static  final String  MOUSEMONITTOR_REPORT_FILE  = "datiMouse.csv";
    /**
     Settings variable: solution screenshot file's name.
     **/
    public static  final String  SCREENSHOTFILE             = "screenshotSoluzione.png";
    /**
     Settings variable: database class's file name.
     **/
    public static  final String  CLASSDATAFILE              = "datiClasse";


    /**
     Settings variable: root databse directory path.
     **/
    public static final String ROOT_DIR = FileSystemView.getFileSystemView().getDefaultDirectory()+SEP+"ScratchTests";
    /**
     Settings variable: classes database path from root directory.
     **/
    public static final String DATABASE_CLASSES  = ROOT_DIR+SEP+"Database\\DatabaseClassi";
    /**
     Settings variable: projects database path from root directory.
     **/
    public static final String DATABASE_PROJECTS = ROOT_DIR+SEP+"Database\\DatabaseProgetti";
    /**
     Settings variable: database folder path from root directory.
     **/
    public static final String DATABASE          = ROOT_DIR+SEP+"Database";
    /**
     Settings variable: reports folder path from root directory.
     **/
    public static final String REPORT            = ROOT_DIR+SEP+"Report";
    /**
     Settings variable: settings file path from root directory.
     **/
    public static final String SETTINGS_FILE     = ROOT_DIR+SEP+"Settings.txt";



    /**
     External Application: name of Scratch executable.
     **/
    public static  String  SCRATCH_EXE      = "Scratch 2"; //Default value
    /**
     External Application: name of mouse monirtor executable.
     **/
    public static  String  MOUSEMONITOR_EXE = "UsageStats";//Default value
    /**
     External Application: name of screen recorder executable.
     **/
    public static  String  SCREENREC_EXE    = "ffmpeg";    //Default value


    /**
     External Application MANDATORY INFORMATION: full path to Scratch executable.
     **/
    public static  String  SCRATCH_FULLPATH;
    /**
     External Application MANDATORY INFORMATION: full path to mouse monitor executable.
     **/
    public static  String  MOUSEMONITOR_FULLPATH;
    /**
     External Application MANDATORY INFORMATION: full path to screen recorder executable.
     **/
    public static  String  SCREENREC_FULLPATH;
    /**
     External Application: full path to mouse monitor backup directory.
     **/
    public static String   MOUSEMONITOR_BACKUP_DIR =
            FileSystemView.getFileSystemView().getDefaultDirectory()+SEP+"UsageStats";        // "C:\\Users\\Anna Bonaldo\\Documents\\UsageStats\\";


    /**
     External Application SETTINGS: mouse monitor report time-span (minutes)
     **/
    public static  int  MOUSEMONITTOR_MIN_TIMESPAN = 10;//Default value
    /**
     External Application SETTINGS: mouse monitor report time-span (seconds)
     **/
    public static  int  MOUSEMONITTOR_SEC_TIMESPAN = 0; //Default value

    /**
     External Application SETTINGS: screen recorder framerate.
     **/

    public static  int  SCREENREC_FR = 30 ;
    /**
     External Application SETTINGS: screen recorder buffer size.
     **/
    public static  int  SCREENREC_BUFFERSIZE = 100;
    /**
     Settings variable: school name. Used in generated report to refer to the current school.
     **/
    public static String SCHOOLID = "Scuola";
    // end todo ------------



    static final String  TXT_SCRATCH_EXE_FULLPATH      = "SCRATCH_EXE_PATH";
    static final String  TXT_MOUSEMONITOR_EXE_FULLPATH = "MOUSEMONITOR_EXE_PATH";
    static final String  TXT_SCREENREC_EXE_FULLPATH    = "SCREENREC_EXE_PATH";
    static final String  TXT_MOUSEMONITOR_BACKUP_DIR   = "MOUSEMONITOR_BACKUP_DIR";

    static final String  TXT_MOUSEMONITTOR_MIN_TIMESPAN = "MOUSEMONITTOR_MIN_TIMESPAN";
    static final String  TXT_MOUSEMONITTOR_SEC_TIMESPAN = "MOUSEMONITTOR_SEC_TIMESPAN";

    static final String  TXT_SCREENREC_FR= "SCREENREC_FR";
    static final String  TXT_SCREENREC_BUFFERSIZE = "SCREENREC_BUFFERSIZE";
    static final String  TXT_SCHOOLID = "SCHOOLID";

    /**
     * Settings Current Value: contains mouse monitor activation status.
     */
    public static Boolean MOUSEMONITOR_ACTIVE = true;
    /**
     * Settings Current Value: contains screen recorder activation status.
     */
    public static Boolean SCREENREC_ACTIVE = true;

    /**
     * Settings Current Value: contains mouse monitor report images activation status.
     */
    public static Boolean  MOUSEDATA_IMAGES = true;
    /**
     * Settings Current Value: contains mouse monitor report activation status.
     */
    public static Boolean  MOUSEDATA_REPORTS = true;

    /**
     * Settings declaration TAG: to set mouse monitor records active with images.
     */
    public static final String  TXT_MOUSEDATA_IMAGES    = "MOUSEDATA_IMAGES";
    /**
     * Settings declaration TAG: to set mouse monitor reports active.
     */
    public static final String  TXT_MOUSEDATA_REPORTS   = "MOUSEDATA_REPORTS";
    /**
     * Settings declaration TAG: to set mouse monitor active.
     */
    public static final String  TXT_MOUSEMONITOR_ACTIVE = "MOUSEMONITOR_ACTIVE";
    /**
     * Settings declaration TAG: to set screen recorder active.
     */
    public static final String  TXT_SCREENREC_ACTIVE    = "SCREENREC_ACTIVE";


    /**
     * It returns the path to the file that contains the student list of class ideentified by classID.
     * @param classID Class identifier.
     * @return Path to student list file.
     */
    public static String  getStudentsListFile(String classID)
    {
       return Path(DATABASE_CLASSES, classID)+SEP+classID+CSV;
    }

    /**
     * It returns the path to the file that contains all student information for class ideentified by classID.
     * @param classID classID Class identifier.
     * @return Path to students' information file
     */
    public static String  getClassDataFile(String classID)
    {
        return Path(DATABASE_CLASSES, classID)+SEP+CLASSDATAFILE+CSV;
    }

    /** Utility method to compose path
     * @param dir Directory full path
     * @param file File name in dir directory.
     * @return The composed path to the file in dir directory.
     */
    public static String Path(String dir, String file )
    {
        return dir+SEP+file;
    }

    /**
     * Launch settings file reading.
     */
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
                line = br.readLine();
        }

       }
       catch (FileNotFoundException e) {
            e.printStackTrace();
       }
       catch (IOException e) {
            e.printStackTrace();
        }
    }


    /** Private static utility methods**/
    private static void ParseLine(String line)
    {
        // PATH PARAMETERS PARSING
        if(line.contains(Settings.TXT_SCRATCH_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_SCRATCH_EXE_FULLPATH, "");
            parseExePath(line, TXT_SCRATCH_EXE_FULLPATH);

        }
        else if(line.contains(Settings.TXT_MOUSEMONITOR_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_MOUSEMONITOR_EXE_FULLPATH, "");
            parseExePath(line, TXT_MOUSEMONITOR_EXE_FULLPATH);

        }
        else if(line.contains(Settings.TXT_SCREENREC_EXE_FULLPATH)) {
            line = line.replace(Settings.TXT_SCREENREC_EXE_FULLPATH, "");
            parseExePath(line, TXT_SCREENREC_EXE_FULLPATH);

        }
        else if(line.contains(Settings.TXT_MOUSEMONITOR_BACKUP_DIR)) {
            line = line.replace(Settings.TXT_MOUSEMONITOR_BACKUP_DIR, "");
            parsePath(line, TXT_MOUSEMONITOR_BACKUP_DIR);
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
        else if(line.contains(Settings.TXT_SCHOOLID)){
            line = line.replace(Settings.TXT_SCHOOLID, "");
            Settings.SCHOOLID = get_CleanValue(line);
        }
        else if(line.contains(Settings.TXT_MOUSEMONITOR_ACTIVE))
        {
            line = line.replace(Settings.TXT_MOUSEMONITOR_ACTIVE, "");
            MOUSEMONITOR_ACTIVE = parseBoolean(line);
        }
        else if(line.contains(Settings.TXT_SCREENREC_ACTIVE))
        {
            line = line.replace(Settings.TXT_SCREENREC_ACTIVE, "");
            SCREENREC_ACTIVE = parseBoolean(line);
        }
        else if(line.contains(Settings.TXT_MOUSEDATA_REPORTS))
        {
            line = line.replace(Settings.TXT_MOUSEDATA_REPORTS, "");
            MOUSEDATA_REPORTS = parseBoolean(line);
        }
        else if(line.contains(Settings.TXT_MOUSEDATA_IMAGES))
        {
            line = line.replace(Settings.TXT_MOUSEDATA_IMAGES, "");
            MOUSEDATA_IMAGES = parseBoolean(line);
        }

    }

    private static String  get_CleanValue(String line)
    {
        String s = line;
        s = s.substring(s.indexOf("=") + 1);
        s = s.substring(0, s.indexOf(";"));
        while(s.contains(" ")) s = s.replace(" ", "");
        return s;
    }

    private static void  parseExePath(String line, String settings)
    {
        String s = line;
        s = s.substring(s.indexOf("\"") + 1);
        s = s.substring(0, s.indexOf("\""));
        File file = new File(s);

        String exefilename =  file.getName().replace(EXE, "");
        String fullpath = file.getAbsolutePath();
        if(settings == TXT_SCRATCH_EXE_FULLPATH){
            SCRATCH_EXE = exefilename; SCRATCH_FULLPATH = fullpath;
        }
        if(settings == TXT_MOUSEMONITOR_EXE_FULLPATH){
            MOUSEMONITOR_EXE = exefilename; MOUSEMONITOR_FULLPATH = fullpath;
        }
        if(settings == TXT_SCREENREC_EXE_FULLPATH){
            SCREENREC_EXE = exefilename; SCREENREC_FULLPATH = fullpath;
        }

    }
    private static void  parsePath(String line, String settings) {
        String s = line;
        s = s.substring(s.indexOf("\"") + 1);
        s = s.substring(0, s.indexOf("\""));
        File file = new File(s);
        if (settings == TXT_MOUSEMONITOR_BACKUP_DIR)
            MOUSEMONITOR_BACKUP_DIR = file.getAbsolutePath();
    }

    private static Boolean parseBoolean(String line){
        String boolVal = get_CleanValue(line);
        if( boolVal.contains("TRUE")  ||  boolVal.contains("true"))
            return true;
        else return false;
    }

}
