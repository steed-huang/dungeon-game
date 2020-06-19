package GameState;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Handler.Keys;
import Handler.Mouse;
import Images.Background;
import Player.Player;
import Player.HUD;
import Player.Item;
import World.Map;
import World.Room;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class LevelState extends GameState {

    private Background bg;
    private Background fog;
    private Map level;
    private Room cur_room;
    private Player player;
    private HUD hud;

    private ArrayList<CollisionBox> cbs = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public LevelState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void init() {
        bg = new Background("/Assets/black.jpg", 0);
        fog = new Background("/Assets/fog.png", 0);

        level = new Map();
        level.generateMap();
        level.printBaseLayout();
        level.printLayout();

        player = new Player(level.getSpawnRow(), level.getSpawnCol());

        hud = new HUD(player);

        cur_room = level.getRoom(player.map_row, player.map_col);

        updateRoom();
    }

    public void init(int score) {} // overloaded

    public void update() {
        player.update(cbs, projectiles, items);
        // will throw ConcurrentModificationException if enhanced for is used
        for (Iterator<Projectile> i = projectiles.iterator(); i.hasNext(); ) {
            Projectile p = i.next();
            p.update(cbs, projectiles, enemies, player);
            if (p.getRemove()) i.remove();
        }
        for (Iterator<Enemy> i = enemies.iterator(); i.hasNext(); ) {
            Enemy e = i.next();
            e.update(cbs, projectiles, enemies, items, player);
            if (!e.getAlive()) { i.remove(); player.addScore(100);};
        }
        items.removeIf(i -> i.getPickedUp());
        handleInput();
        doorCheck();

        if (!player.getAlive()) { // player died
            gsm.setState(GameStateManager.LOST, player.getScore());
        }
    }

    public void doorCheck() {
        if (player.y_r_pos() < 25){ // up
            player.setRoomPosition(player.x_r_pos(), 1474);
            cur_room = level.getRoom(player.map_row-=1, player.map_col);
            updateRoom();
        }
        else if (player.y_r_pos() > 1475){ // down
            player.setRoomPosition(player.x_r_pos(), 26);
            cur_room = level.getRoom(player.map_row+=1, player.map_col);
            updateRoom();
        }
        else if (player.x_r_pos() < 25){ // left
            player.setRoomPosition(1474, player.y_r_pos());
            cur_room = level.getRoom(player.map_row, player.map_col-=1);
            updateRoom();
        }
        else if (player.x_r_pos() > 1475){ // right
            player.setRoomPosition(26, player.y_r_pos());
            cur_room = level.getRoom(player.map_row, player.map_col+=1);
            updateRoom();
        }
    }

    public void updateRoom(){
        projectiles.clear();

        enemies = cur_room.getEnemies();
        cbs = cur_room.getCBS();
        items = cur_room.getItems();
        //System.out.printf("[%d, %d] \n", player.map_row, player.map_col);
    }



    public void draw(Graphics2D g) {
        int x = player.x_r_pos();
        int y = player.y_r_pos();
        bg.draw(g);
        cur_room.draw(g, x, y);
        for (Projectile p : projectiles) {
            p.draw(g, x, y);
        }
        for (Item i : items) {
            i.draw(g, x, y);
        }
        player.draw(g);
        for (Enemy e : enemies) {
            e.draw(g, x, y);
        }
        fog.draw(g);
        hud.draw(g, player.getInv().getAbility().getReady());
        player.getInv().draw(g);

        // for (CollisionBox cb : cbs){ cb.draw(g, x, y); }
    }

    public void handleInput() {
        // firing
        if (Mouse.isHeld()) player.setFiring(true); else player.setFiring(false);

        // ability
        if (Keys.isHeld(Keys.SPACE)) player.setAbilityFiring(true); else player.setAbilityFiring(false);

        // pick up
        if (Keys.isPressed(Keys.E)) player.setPicking(true); else player.setPicking(false);

        // movement
        if (Keys.isHeld(Keys.W)) player.setUp(true); else player.setUp(false);
        if (Keys.isHeld(Keys.A)) player.setLeft(true); else player.setLeft(false);
        if (Keys.isHeld(Keys.S)) player.setDown(true); else player.setDown(false);
        if (Keys.isHeld(Keys.D)) player.setRight(true); else player.setRight(false);
    }
}