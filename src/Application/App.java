package Application;

import Application.Database.DatabaseManager;
import Application.GUI.GUITestManager;
import Application.TestController.ToolController;

/**
 * Main program class.
 */
public class App {

    static void StartConfigurationUI() {
        GUITestManager.ShowConfigurationFrame();
    }

    public static void StartTestSession()
    {
       ToolController.Start();
       GUITestManager.StartSessionExecutionUI();
    }

    public static void EndTestSession() {
        ToolController.Stop();
    }

    public static void ConfirmTestConfiguration(){
        DatabaseManager.ConfirmConfiguration();
        GUITestManager.ShowSessionStartFrame();
    }

    /** Main program method: It performs settings reading and launches ConfigurationUI window.
     * @param args Command line input patameters. No command input parameters are used.
     */
    public static void main(String args[]) {
     try{
         Settings.readSettings();
         StartConfigurationUI();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
