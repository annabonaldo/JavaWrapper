import GUI.GUIManager;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class App {
    static App instance;

    static void StartGUI() {

        GUIManager.showFrame();
    }

    static void StartMonitors(){

    }


    public static void main(String args[]) {
        try{



        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
