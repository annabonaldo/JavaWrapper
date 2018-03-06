import GUI.GUIManager;
import TestMonitor.ExteralProcessController;
import TestMonitor.DesktopRecorder;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class App {

    static DesktopRecorder desktopRecorder = new DesktopRecorder();
    static App instance;

    static void StartGUI() {

        GUIManager.showFrame();
    }

    static void StartMonitors(){
        desktopRecorder.run();
    }

    static void WaitOnMonitors(){
        try {
            desktopRecorder.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try{

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
