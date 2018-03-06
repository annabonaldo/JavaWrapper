package GUI;

import Application.DatabaseManager;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class chooseStudent extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;
    chooseStudent dialog;


    public chooseStudent() {
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
        DatabaseManager.SetStudentId(dialog.list1.getSelectedIndex());
        //AppGUI.UpdateStartIfEnabled();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void showStudentDialog(ArrayList<String> studentList) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String student:studentList) { model.addElement(student); }
        chooseStudent dialog = new chooseStudent();
        dialog.list1.setModel(model);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
