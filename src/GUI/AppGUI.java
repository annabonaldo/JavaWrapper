package GUI;

import Application.DatabaseManager;
import Application.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class AppGUI {
    public  JPanel appMain;
    public  JButton impostazioniButton;
    private JButton INIZIAButton;
    private JButton BTNchoooseStudent;
    private JButton BTNchooseClass;


   // public void UpdateStartIfEnabled(){
     //   INIZIAButton.setEnabled(DatabaseManager.ParametersOk());
    //}

    AppGUI() {
            BTNchoooseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseStudent.showStudentDialog(DatabaseManager.StudentsList(DatabaseManager.ClassID()));

            }
        });

        BTNchooseClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chooseClass.showClassDialog();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void ShowStartFrame()
    {
        JFrame frame = new JFrame("Scratch Test");
        AppGUI appGUI = new AppGUI();
        frame.setContentPane(appGUI.appMain);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        Settings.readSettings();
        ShowStartFrame();
    }
}
