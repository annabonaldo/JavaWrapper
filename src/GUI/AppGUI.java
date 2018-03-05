package GUI;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

/**
 * Created by Anna Bonaldo on 05/03/2018.
 */
public class AppGUI {
    public JPanel appMain;
    public JButton esciButton;
    public JButton impostazioniButton;

    public static void main(String args[]){
        JFrame frame = new JFrame("Scratch Test");
        AppGUI gui = new AppGUI();
        frame.setContentPane(gui.appMain);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
