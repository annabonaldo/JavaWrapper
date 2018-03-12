package GUI;

import Application.DatabaseManager;
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
    public  JButton impostazioniButton;
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
    private JButton button1;
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
                TestSession session  = new TestSession();
                session.show();
            }
        });
        UpdateBTNVisibility();
    }

    public  void ShowStartFrame()
    {
        mainFrame = new JFrame("Scratch Test");
        mainFrame.setContentPane(appConfigMain);

        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

    }

    public static void main(String args[]){
        Settings.readSettings();
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.ShowStartFrame();
    }

    public void UpdateBTNVisibility() {

        boolean hasProject = DatabaseManager.HasProject();
        boolean hasClass   = DatabaseManager.HasClass();
        boolean hasStudent = DatabaseManager.HasStudent();

        boolean showChooseProject = !hasProject;
        boolean showChooseClass = hasProject && !hasClass;
        boolean showChooseStudent = hasClass && hasProject && !hasStudent;
        boolean haveConfig = ( hasProject && hasClass && hasStudent);

        if(hasProject){System.out.println("hasProject");}
        if(hasClass)  {System.out.println("hasClass");}
        if(hasStudent){System.out.println("hasStudent");}
        if(showChooseProject){System.out.println("showChooseProject");}
        if(showChooseClass){System.out.println("showChooseClass");}
        if(showChooseStudent){System.out.println("showChooseStudent");}
        if(haveConfig){System.out.println("haveConfig");}


        BTNchoooseStudent.setVisible(true);
        BTNConfigConfirm.setVisible(true);
        BTNchooseClass.setVisible(true);
        BTNchooseProject.setVisible(true);

        if(showChooseProject)
        {
            BTNchooseProject.setBackground(Color.LIGHT_GRAY);
            BTNchooseClass.setBackground(Color.LIGHT_GRAY);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else
        if(showChooseClass) {
            BTNchooseProject.setBackground(Color.GREEN);
            BTNchooseClass.setBackground(Color.LIGHT_GRAY);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else if(showChooseStudent){
            BTNchooseProject.setBackground(Color.GREEN);
            BTNchooseClass.setBackground(Color.GREEN);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
        }
        else
        if(haveConfig){
            BTNchooseProject.setBackground(Color.LIGHT_GRAY);
            BTNchooseClass.setBackground(Color.LIGHT_GRAY);
            BTNchoooseStudent.setBackground(Color.LIGHT_GRAY);
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

        System.out.println("TestConfiguration Student List");
        for(String student: studentList)
            System.out.println("Student: "+ student);

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
