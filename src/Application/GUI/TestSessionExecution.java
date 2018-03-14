package Application.GUI;

import javax.swing.*;
import java.awt.event.*;

public class TestSessionExecution extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;


    public TestSessionExecution() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
        GUITestManager.EndSessionCommand();
    }


    public static void Show() {
        TestSessionExecution dialog = new TestSessionExecution();
        dialog.setUndecorated(true);
        dialog.setModal (true);
        dialog.setAlwaysOnTop (true);
        dialog.setModalityType (ModalityType.APPLICATION_MODAL);
        dialog.pack();
        dialog.setVisible(true);
    }
}
