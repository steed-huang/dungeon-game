package GameState;

import Handler.Keys;
import Images.Background;
import Player.Player;
import World.Map;

import java.awt.*;

public class LevelState extends GameState {

    private Background bg;
    private Map level;
    private Player player;

    public LevelState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void init() {
        bg = new Background("/Assets/menubg.jpg", 0);

        level = new Map();
        level.generateMap();
        level.printBaseLayout();
        level.printLayout();

        player = new Player();
    }

    public void update() {
        player.update();
        handleInput();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        player.draw(g);
    }

    public void handleInput() {
        if (Keys.isHeld(Keys.W)) player.setUp(true); else player.setUp(false);
        if (Keys.isHeld(Keys.A)) player.setLeft(true); else player.setLeft(false);
        if (Keys.isHeld(Keys.S)) player.setDown(true); else player.setDown(false);
        if (Keys.isHeld(Keys.D)) player.setRight(true); else player.setRight(false);
    }
}