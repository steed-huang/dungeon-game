package GameState;

import java.util.ArrayList;

public class GameStateManager {
    // arraylist to hold all gamestates
    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int LEVEL = 1;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new LevelState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }
}
