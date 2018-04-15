package Application.GUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * GUI to ask the user to confirm the end of current session.
 */
public class endTestSessionCheck extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    static   private boolean endSession = false;

    /**
     * Terminates test session ending checks.
     */
    public endTestSessionCheck() {
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
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void onOK() {
        endSession = true;
        this.dispose();

    }

    private void onCancel() {
        this.dispose();
    }

    /**
     * Execute controls before session end.
     */
    public static void CheckEndSession () {
        endTestSessionCheck dialog = new endTestSessionCheck();
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Checks session must be terminated.
     * @return if true session is confirmed to be ended.
     */
    public static boolean isEndSession() {
        return endSession;
    }
}
