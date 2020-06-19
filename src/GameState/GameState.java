package GameState;

import java.awt.*;

public abstract class GameState {
    protected GameStateManager gsm;
    public abstract void init();
    public abstract void init(int score);
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void handleInput();
}
