import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class StartApp {

    static Timer timer;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        //frame.setVisible(true);
        frame.repaint();
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
            }
        }, 0, 800);



    }
}
