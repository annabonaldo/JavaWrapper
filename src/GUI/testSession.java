package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Created by Anna Bonaldo on 12/03/2018.
 */
public class TestSession {
    private JButton BTNesci;
    private JPanel testSessionPanel;
    private JButton BTNinizia;


    public TestSession() {
        BTNinizia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        BTNesci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public  void show()
    {
        JFrame frame = new JFrame("Inizia Sessione di Test Scratch");
        frame.setContentPane(testSessionPanel);

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }
}
