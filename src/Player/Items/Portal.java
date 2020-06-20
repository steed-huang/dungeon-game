package Player.Items;

import GameState.GameStateManager;
import GameState.LevelState;
import Images.ImageLoader;
import Player.Inventory;
import Player.Item;
import Player.Player;

import java.util.ArrayList;

public class Portal extends Item {

    private GameStateManager gsm;

    public Portal(int room_x, int room_y, GameStateManager gsm) {
        super("portal", room_x, room_y, false);

        sprite = ImageLoader.getImage("portal.png");

        this.width = 100;
        this.height = 100;

        this.gsm = gsm;
    }

    public void pickUp(Inventory inv, ArrayList<Item> items, Player player) {
        LevelState.current_level += 1;
        if (LevelState.current_level == 4) {
            gsm.setState(GameStateManager.WON, player.getScore()); // you win
            LevelState.restart = true;
            LevelState.current_level = 1;
        }
        else gsm.setState(GameStateManager.LEVEL); // next level
    }
}
