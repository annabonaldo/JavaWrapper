package Application.GUI;

import Application.App;
import Application.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * This class manages all GUI classes during session configuration and execution.
 */
public class GUITestManager {
    /**
     * Show session configuration frame.
     */
    public static void ShowConfigurationFrame(){

        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.Show();
    }

    /**
     * Shows frame the user can use to start the current session.
     */
    public static void ShowSessionStartFrame(){
        TestSession session  = new TestSession();
        session.show();
    }

    /**
     * Launch start session command.
     */
    static void StartSessionCommand()
    {
        App.StartTestSession();
    }

    /**
     * Starts session exection GUI.
     */
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

    /**
     * Call for end session confirmation from the user.
     */
    public static void CheckEndSessionCommand() {
        endTestSessionCheck.CheckEndSession();
    }

    /**
     * Launch end session command
     */
    public static void EndSessionCommand() {
        CheckEndSessionCommand();
        if(endTestSessionCheck.isEndSession())
        {
            App.EndTestSession();
        }
        else {
            TestSessionExecution.Show();
        }

    }

}
