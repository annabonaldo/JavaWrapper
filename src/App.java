import GUI.GUIManager;
import TestMonitor.DesktopRecorder;
import TestMonitor.MouseMonitor;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class App {
    static  MouseMonitor mouseMonitor = new MouseMonitor();
    static DesktopRecorder desktopRecorder = new DesktopRecorder();
    static App instance;

    static void StartGUI() {

        GUIManager.showFrame();
    }

    static void StartMonitors(){
        mouseMonitor.run();
        desktopRecorder.run();
    }

    static void WaitOnMonitors(){
        try {
            mouseMonitor.wait();
            desktopRecorder.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try{
            System.out.println("prova start");

            System.out.println("prova");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
