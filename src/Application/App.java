package Application;

import Application.Database.DatabaseManager;
import Application.GUI.GUITestManager;
import Application.TestController.ToolController;

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
