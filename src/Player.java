/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Rectangle;

/**
 *
 * @author vojtechkylar
 */
public class Player {

    GamePanel panel;
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    String move;


    public Player(GamePanel panel){
        this.panel = panel;

    }

    public String getMove(){

        return move;
    }

}
