import org.apache.groovy.groovysh.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        initComponents();

    }

    GamePanel panel;

    private void initComponents() {
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        panel = new GamePanel(this);
        this.add(panel);
        addKeyListener(new KeyChecker(panel));

        setVisible(true);
    }

    //TODO startovací menu
    //TODO vytvořit tabulku slávy
    //TODO přidat nastavení
    //TODO přidat výběr levelů

    @Override
    public void paint(Graphics g) {
        Graphics2D gtd = (Graphics2D) g;
        panel.repaint();
    }
}
