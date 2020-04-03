package GameState;

import Entity.CollisionBox;
import Handler.Keys;
import Images.Background;
import Player.Player;
import World.Map;
import World.Room;

import java.awt.*;
import java.util.ArrayList;

public class LevelState extends GameState {

    private Background bg;
    private Map level;
    private Room cur_room;
    private Player player;

    private ArrayList<CollisionBox> cbs = new ArrayList<>();

    public LevelState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void init() {
        bg = new Background("/Assets/black.jpg", 0);

        level = new Map();
        level.generateMap();
        level.printBaseLayout();
        level.printLayout();

        player = new Player(level.getSpawnRow(), level.getSpawnCol());

        cur_room = level.getRoom(player.map_row, player.map_col);
        cbs.addAll(cur_room.getCBS());
    }

    public void update() {
        player.update(cbs);
        handleInput();
    }

    public void draw(Graphics2D g) {
        int x = player.x_r_pos();
        int y = player.y_r_pos();
        bg.draw(g);
        cur_room.draw(g, x, y);
        for (CollisionBox cb : cbs){
            cb.draw(g, x, y);
        }
        player.draw(g);
    }

    public void handleInput() {
        if (Keys.isHeld(Keys.W)) player.setUp(true); else player.setUp(false);
        if (Keys.isHeld(Keys.A)) player.setLeft(true); else player.setLeft(false);
        if (Keys.isHeld(Keys.S)) player.setDown(true); else player.setDown(false);
        if (Keys.isHeld(Keys.D)) player.setRight(true); else player.setRight(false);
    }
}