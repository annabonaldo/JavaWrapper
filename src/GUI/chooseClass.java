package GUI;

import Application.DatabaseManager;
import Application.Settings;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class chooseClass extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList classesList;

    static private chooseClass dialog;

    public chooseClass() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        DatabaseManager.SetClass(dialog.classesList.getSelectedValue().toString());
       // AppGUI.UpdateStartIfEnabled();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void showClassDialog() throws IOException {

        HashMap<String, String> classesMAP = new HashMap<>();

        File dir = new File(Settings.DBPath());
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("directory:" + file.getAbsolutePath());
                classesMAP.put(file.getName(), file.getAbsolutePath());
            }
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        classesMAP.keySet().forEach(model::addElement);

        dialog = new chooseClass();
        dialog.classesList.setModel(model);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
