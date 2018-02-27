package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anna Bonaldo on 22/02/2018.
 */
public class Session {
    private JButton INIZIAILTESTButton;
    private JButton esciButton;
    private JButton impostazioniButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public Session() {

        INIZIAILTESTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        impostazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
