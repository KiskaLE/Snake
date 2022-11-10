import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends JPanel {


    private String src;
    BufferedImage img;

    public Image(String src) {
        this.src = src;
        try {
            img = ImageIO.read(new File(src));
        } catch (IOException e) {
            System.out.println("no image found");
        }
        setVisible(true);
    }

    public void setImage(String url){
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
            System.out.println("no image found");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        draw(gtd);
    }
    private void draw(Graphics2D gtd){
        gtd.drawImage(img,0,0,getWidth(),getHeight(), this);
    }
}
