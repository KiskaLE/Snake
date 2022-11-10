import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    private MainFrame frame;
    private BufferedImage img;
    List<Image> imgs;
    private List<SnakeBody> bodies;
    private List<Food> foods;
    /**
     * 0 - nic
     * 1 - hlava hada
     * 2 - tělo hada
     * 3 - jídlo
     **/
    private int[][] map;
    Player player;
    int width;
    int height;

    public GamePanel(MainFrame frame) {
        this.frame = frame;
        foods = new ArrayList<>();
        initComponents();

    }

    private void run() {
        try {
            move(player.getMove());

        }catch (Exception e) {

        }
        makeMap();
        spawnFood();
        update();
    }

    private void initComponents() {
        setBounds(0, 0, frame.getWidth(), frame.getHeight() - 100);
        setBackground(Color.blue);
        width = 5;
        height = 5;
        player = new Player(this);
        bodies = new ArrayList<>();
        bodies.add(new SnakeBody(height / 2, width / 2));

        setLayout(new GridLayout(height, width));
        makeMap();
        initMap();


    }
    //TODO přidat tělo po snězení jídla
    //TODO Přidat kolize
    //TODO Otáčet hlavu podle směru
    //TODO přidat indikátor skóre
    //TODO přidat Game Over se zadáním jména pro uložení do tabulky
    private void spawnFood(){
        if (foods != null && foods.size() < 1) {
            List<Location> possibleFoodLocation = new ArrayList<>();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 0){
                        possibleFoodLocation.add(new Location(i, j));
                    }
                }
            }
            Location randomLocation = possibleFoodLocation.get((int) Math.round(Math.random()*possibleFoodLocation.size()));
            foods.add(new Food(randomLocation.getX(),randomLocation.getY()));
        }
    }
    private void initMap(){
        imgs = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            String url = "";
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        url = "img/blankBlock.png";
                        break;
                    case 1:
                        url = "img/snake-head.png";
                        break;
                    case 2:
                        url = "img/snake-body.png";
                        break;
                    case 3:
                        url = "img/food.png";
                        break;
                    default:
                        url = "img/blankBlock.png";
                        break;
                }
                //add(new JButton(i+""));
                Image temp = new Image(url);
                imgs.add(temp);
                add(temp);

            }
        }
    }

    private void makeMap() {
        map = new int[height][width];
        addFoods();
        SnakeBody head = bodies.get(0);
        try{
        map[head.getX()][head.getY()] = 1;
            System.out.println(Arrays.deepToString(map));

        }catch (Exception e){
            System.out.println("error");
        }
        for (int i = 1; i < bodies.size(); i++) {
            SnakeBody body = bodies.get(i);
            map[body.getX()][body.getY()] = 2;
        }

    }

    private void update(){

        int count = 0;
        for (int i = 0; i < map.length; i++) {
            String url = "";
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        url = "img/blankBlock.png";
                        break;
                    case 1:
                        url = "img/snake-head.png";
                        break;
                    case 2:
                        url = "img/snake-body.png";
                        break;
                    case 3:
                        url = "img/food.png";
                        break;
                    default:
                        url = "img/blankBlock.png";
                        break;
                }
                Image img = imgs.get(count);
                img.setImage(url);
                count++;

            }
        }
    }

    private void addFoods(){
        if (foods != null && foods.size() > 0) {
            for (Food food:
                    foods) {
                map[food.getX()][food.getY()] = 3;
            }
        }


    }

    private boolean isFood(SnakeBody head){
        for (int i = 0; i < foods.size(); i++) {
            Food get = foods.get(i);
            if (head.getX() == get.getX() && head.getY() == get.getY()){
                return true;
            }

        }
        return  false;
    }

    //TODO přidat pohyb těla
    private void move(String move) {
        SnakeBody head = bodies.get(0);
        switch (move) {
            case "up":
                head.setX(head.getX() - 1);
                break;
            case "down":
                head.setX(head.getX() + 1);
                break;
            case "left":
                head.setY(head.getY() - 1);
                break;
            case "right":
                head.setY(head.getY() + 1);
                break;

        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gtd = (Graphics2D) g;
        draw(gtd);
    }

    private void draw(Graphics2D gtd) {
        run();
        for (Image img :
                imgs) {
            img.repaint();
        }
    }





    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='a') player.move = "left";
        if (e.getKeyChar()=='w') player.move = "up";
        if (e.getKeyChar()=='d') player.move = "right";
        if (e.getKeyChar()=='s') player.move = "down";
    }

    public void keyReleased(KeyEvent e) {
        //if (e.getKeyChar()=='a') player.keyLeft = false;
        //if (e.getKeyChar()=='w') player.keyUp = false;
        //if (e.getKeyChar()=='d') player.keyRight = false;
        //if (e.getKeyChar()=='s') player.keyDown = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
