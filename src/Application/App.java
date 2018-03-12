package Application;

import Application.Database.DatabaseManager;
import Application.GUI.GUITestManager;
import Application.GUI.TestSession;
import Application.TestController.ToolController;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class App {

    static void StartConfigurationUI() {

        GUITestManager.ShowConfigurationFrame();
    }

    public static void StartTestSession()
    {
        ToolController.StartSession();
    }


    public static void main(String args[]) {
        try{
            Settings.readSettings();
           // StartConfigurationUI();
            StartTestSession();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
