package Application.GUI;

import Application.App;
import Application.Database.DatabaseManager;
import Application.Settings;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class TestConfiguration {

    private static final String rootTreeProjectName = "Progetti di test";
    public  JPanel appConfigMain;
    private JButton BTNConfigConfirm;
    private JButton BTNchoooseStudent;
    private JButton BTNchooseClass;
    private JList chooseClassList;
    private JList chooseStudentList;
    private JButton BTNchooseProject;
    private JTree chooseProjectTree;
    private JScrollPane chooseClassPanel;
    private JScrollPane chooseProjectPanel;
    private JScrollPane chooseStudentPanel;
    private JLabel InstructionsMainLabel;
    private JLabel chooseStudentLabel;
    private JLabel chooseClassLabel;
    private JLabel ChooseProjectLabel;
    private JButton BTNReset;
    JFrame  mainFrame;


    TestConfiguration() {

        this.chooseStudentList.setVisible(false);
        LoadClassList();
        LoadProjectsList();

        appConfigMain.addComponentListener(new ComponentAdapter() {});


        BTNchoooseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseStudentList.getSelectedValue() != null) {
                    DatabaseManager.SetStudentId(chooseStudentList.getAnchorSelectionIndex()+1);
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
                    UpdateBTNVisibility();
                }

            }
        });
        BTNchooseProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath path = chooseProjectTree.getSelectionPath();
                String project = path.getLastPathComponent().toString();
                String test = path.getParentPath().getLastPathComponent().toString();
                if (!test.contains(rootTreeProjectName)) {
                    DatabaseManager.SetProject(test, project);
                }
                UpdateBTNVisibility();
            }
        });
        BTNConfigConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                App.ConfirmTestConfiguration();
            }
        });

        BTNReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseManager.ResetConfig();
                UpdateBTNVisibility();
            }
        });
        UpdateBTNVisibility();
    }

    public  void Show()
    {
        mainFrame = new JFrame("Scratch Test");
        mainFrame.setContentPane(appConfigMain);

        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

    }

    public void UpdateBTNVisibility() {

        boolean hasProject = DatabaseManager.HasProject();
        boolean hasClass   = DatabaseManager.HasClass();
        boolean hasStudent = DatabaseManager.HasStudent();

        boolean showChooseProject = !hasProject;
        boolean showChooseClass = hasProject && !hasClass;
        boolean showChooseStudent = hasClass && hasProject && !hasStudent;
        boolean haveConfig = ( hasProject && hasClass && hasStudent);



        BTNchoooseStudent.setVisible(true);
        BTNConfigConfirm.setVisible(true);
        BTNchooseClass.setVisible(true);
        BTNchooseProject.setVisible(true);

        BTNchooseProject.setForeground(Color.DARK_GRAY);
        BTNchooseClass.setForeground(Color.DARK_GRAY);
        BTNchoooseStudent.setForeground(Color.DARK_GRAY);

        if(showChooseProject)
        {
            BTNchooseProject.setBackground(Color.LIGHT_GRAY);
            BTNchooseClass.setBackground(Color.LIGHT_GRAY);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else
        if(showChooseClass) {
            BTNchooseProject.setBackground(Color.GREEN);
            BTNchooseProject.setForeground(Color.BLACK);
            BTNchooseClass.setBackground(Color.LIGHT_GRAY);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else if(showChooseStudent){
            BTNchooseProject.setBackground(Color.GREEN);
            BTNchooseClass.setBackground(Color.GREEN);
            BTNchooseProject.setForeground(Color.BLACK);
            BTNchooseClass.setForeground(Color.BLACK);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else
        if(haveConfig){
            BTNchooseProject.setBackground(Color.GREEN);
            BTNchooseClass.setBackground(Color.GREEN);
            BTNchoooseStudent.setBackground(Color.GREEN);
            BTNchooseProject.setForeground(Color.BLACK);
            BTNchooseClass.setForeground(Color.BLACK);
            BTNchoooseStudent.setForeground(Color.BLACK);
        }
        chooseProjectPanel.setVisible(showChooseProject);
        chooseProjectTree.setVisible(showChooseProject);
        chooseClassPanel.setVisible(showChooseClass);
        chooseClassList.setVisible(showChooseClass);

        chooseStudentPanel.setVisible(showChooseStudent);
        chooseStudentList.setVisible(showChooseStudent);

        BTNConfigConfirm.setEnabled(haveConfig);
        BTNchoooseStudent.setEnabled(showChooseStudent);
        BTNchooseClass.setEnabled(showChooseClass);
        BTNchooseProject.setEnabled(showChooseProject);
        appConfigMain.updateUI();

    }

    public void LoadClassList() {

        HashMap<String, String> classesMAP = new HashMap<>();

        // TODO move to DatabaseManager
        File dir = new File(Settings.DATABASE_CLASSES);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classesMAP.put(file.getName(), file.getAbsolutePath());
            }
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        classesMAP.keySet().forEach(model::addElement);
        chooseClassList.setModel(model);

    }

    public void LoadStudentsList() {

        ArrayList<String> studentList = DatabaseManager.StudentsList();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String student:studentList) { model.addElement(student); }
        chooseStudentList.setModel(model);

    }

    public void LoadProjectsList() {
        HashMap<String, String> projectsMAP = new HashMap<>();

        // TODO move to DatabaseManager
        File dir = new File(Settings.DATABASE_PROJECTS);
        File[] files = dir.listFiles();

        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(rootTreeProjectName);
        DefaultTreeModel model  = new DefaultTreeModel(top);
        for (File test : files) {
            if (test.isDirectory() && (test.listFiles()!=null)){
                DefaultMutableTreeNode testFolder = new DefaultMutableTreeNode(test.getName());
                for (File project : test.listFiles()) {
                    testFolder.add(new DefaultMutableTreeNode(project.getName()));
                }
                top.add(testFolder);
            }
        }
        chooseProjectTree.setModel(model);
    }
}
