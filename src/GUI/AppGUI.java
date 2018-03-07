package GUI;

import Application.DatabaseManager;
import Application.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class AppGUI {
    public  JPanel appMain;
    public  JButton impostazioniButton;
    private JButton BTNConfigConfirm;
    private JButton BTNchoooseStudent;
    private JButton BTNchooseClass;
    private JList chooseClassList;
    private JList chooseStudentList;




    AppGUI() {

        this.chooseStudentList.setVisible(false);
        LoadClassList();
        BTNchoooseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseStudentList.getSelectedValue() != null) {
                    DatabaseManager.SetStudentId(chooseStudentList.getAnchorSelectionIndex());
                }
                UpdateBTNVisibility();

            }
        });


        BTNchooseClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseClassList.getSelectedValue() != null) {
                    DatabaseManager.SetClass(chooseClassList.getSelectedValue().toString());
                    LoadStudentsList();
                   // UpdateBTNVisibility();
                }

            }
        });
        appMain.addComponentListener(new ComponentAdapter() {});

        BTNchoooseStudent.setVisible(true);
        BTNchoooseStudent.setEnabled(true);
        chooseClassList.setVisible(true);
        chooseClassList.setEnabled(true);
        chooseStudentList.setVisible(true);
        chooseStudentList.setEnabled(true);
        UpdateBTNVisibility();
    }

    public  void ShowStartFrame()
    {
        JFrame frame = new JFrame("Scratch Test");
        frame.setContentPane(appMain);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        Settings.readSettings();
        AppGUI appGUI = new AppGUI();
        appGUI.ShowStartFrame();
    }

    public void UpdateBTNVisibility() {
        Boolean haveConfig = (DatabaseManager.HasClass() && DatabaseManager.HasStudent());
        BTNConfigConfirm.setEnabled(haveConfig);
        BTNConfigConfirm.setVisible(true);


    }

    public void LoadClassList() {

        HashMap<String, String> classesMAP = new HashMap<>();

        // TODO move to DatabaseManager
        File dir = new File(Settings.Path(Settings.DIR.DIR_DatabaseClassi));
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("directory:" + file.getAbsolutePath());
                classesMAP.put(file.getName(), file.getAbsolutePath());
            }
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        classesMAP.keySet().forEach(model::addElement);
        chooseClassList.setModel(model);

    }

    public void LoadStudentsList() {

        ArrayList<String> studentList = DatabaseManager.StudentsList();

        System.out.println("AppGUI Student List");
        for(String student: studentList)
            System.out.println("Student: "+ student);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String student:studentList) { model.addElement(student); }

        chooseStudentList.setModel(model);


    }
}
