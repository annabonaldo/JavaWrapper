package TestMonitor;
/**
 * Created by Anna Bonaldo on 15/01/2018.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DesktopRecorder implements Runnable{

    int _waitMilliSec;
    Rectangle _screen;
    AtomicBoolean _stop;
    long _pictureID= 0;
    String _path;

    DesktopRecorder()
    {
        // frame per second = 1000 ms(= 1 sec) / num frame per second
        _waitMilliSec = 1000 / Settings.get_frameRate();
        _screen =  new Rectangle(Settings.get_height(), Settings.get_width());
        _path = Settings.get_ScreenImgsDir();
    }

    @Override
    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        while(!_stop.get()) {
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

    private void save_picture(BufferedImage screenCapture) throws IOException {
        File outputfile = new File(_path + Long.toString(_pictureID++)+".jpg");
        ImageIO.write(screenCapture, "jpg", outputfile);

    }

    public void stop_recording()
    {
        _stop.set(true);
    }
}
