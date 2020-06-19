package GameState;

import Handler.Keys;
import Images.Background;

import java.awt.*;

public class ControlsState extends GameState {

    private Background bg;

    public ControlsState(GameStateManager gsm) {
        this.gsm = gsm;
        try{
            bg = new Background("/Assets/tutorial.png", 0);
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
