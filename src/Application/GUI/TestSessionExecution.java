package Application.GUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * GUI for terminating test during session execution. 
 */
public class TestSessionExecution extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;


    /**
     * Build test session execution control form.
     */
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

    /**
     * Shows test session execution controller (to end current executing session).
     */
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
