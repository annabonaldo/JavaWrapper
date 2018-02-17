/**
 * Created by Anna Bonaldo on 27/12/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Robot;

public class ExternalApp {
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];
    public static void main(String args[]) {
        try{
           // String[] cmdArray = new String[]{"C:\\Program Files (x86)\\Scratch 2\\Scratch 2.exe\\  "};
            showFrame();
            String[] cmdArray = new String[]{"C:\\Users\\Anna Bonaldo\\Desktop\\scratch_start.bat"};
            Runtime.getRuntime().exec(cmdArray);

            String[] endCmdArray = new String[]{"C:\\Users\\Anna Bonaldo\\Desktop\\scratch_end.bat"};
            Runtime.getRuntime().exec(endCmdArray);


        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



    public static void showFrame() {

        final JFrame frame = new JFrame("Display Mode");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        JButton btn1 = new JButton("Full-Screen");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device.setFullScreenWindow(frame);
            }
        });
        JButton btn2 = new JButton("Normal");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device.setFullScreenWindow(null);
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn1);
        panel.add(btn2);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
