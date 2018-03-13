package Application.GUI;

import Application.App;
import Application.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class GUITestManager {
    public static void ShowConfigurationFrame(){

        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.Show();
    }

    static void ShowSessionStartFrame(){
        TestSession session  = new TestSession();
        session.show();
    }

    static void StartSessionCommand()
    {
        App.StartTestSession();
    }

    public static void StartSessionExecutionUI(){
        TestSessionExecution.Show();
    }


    public static void showFrame() {

        final JFrame frame = new JFrame("Display Mode");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        JButton btn1 = new JButton("Full-Screen");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn1);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void CheckEndSessionCommand() {
        endTestSessionCheck.CheckEndSession();
    }

    public static void EndSessionCommand() {
        CheckEndSessionCommand();
        if(endTestSessionCheck.isEndSession())
        {

            System.out.println("END SESSION");
            App.EndTestSession();
        }
        else {
            TestSessionExecution.Show();
            System.out.println("CONTINUE SESSION");
        }

    }

    /*public static void EndSessionCancelled() {
        TestSessionExecution.Show();
    }*/
}
