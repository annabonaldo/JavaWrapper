package TestMonitor;
/**
 * Created by Anna Bonaldo on 15/01/2018.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DesktopRecorder extends Monitor{

    int _waitMilliSec;
    Rectangle _screen;
    long _pictureID= 0;
    String _path;
    Robot robot;

    public DesktopRecorder()
    {
        super();
        // frame per second = 1000 ms(= 1 sec) / num frame per second
     //   _waitMilliSec = 1000 / Settings.get_frameRate();
   //    _screen =  new Rectangle(Settings.get_height(), Settings.get_width());
   //     _path = Settings.get_ScreenImgsDir();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void save_picture(BufferedImage screenCapture) throws IOException {
        File outputfile = new File(_path + Long.toString(_pictureID++)+".jpg");
        ImageIO.write(screenCapture, "jpg", outputfile);

    }

    @Override
    void PreRunOperations() {

    }

    @Override
    void PostRunOperations() {

    }

    @Override
    void CollectData(){
        BufferedImage screenCapture = robot.createScreenCapture(_screen);
        Long startTime = System.currentTimeMillis();
        try {
            save_picture(screenCapture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long duration = startTime - System.currentTimeMillis();
        robot.delay(_waitMilliSec- duration.intValue());

    }

}
