package GameState;

import Handler.Keys;
import Images.Background;

import java.awt.*;

public class AboutState extends GameState {

    private Background bg;

    public AboutState(GameStateManager gsm) {
        this.gsm = gsm;
        try{
            bg = new Background("/Assets/about.png", 0);
            bg.setVector(0, 0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init() {}

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            gsm.setState(GameStateManager.MENU);
        }
    }
}
