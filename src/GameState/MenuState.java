package GameState;

import Handler.Keys;
import TileMap.Background;
import java.awt.*;

public class MenuState extends GameState {

    private Background bg;
    private int currentChoice = 0;
    private String[] buttons = {"Play", "Options", "Tutorial", "Quit"};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        try{
            bg = new Background("/Assets/bg.jpg", 1);
            bg.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Roboto", Font.PLAIN, 28);

            font = new Font("Arial", Font.PLAIN, 12);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init() {

    }

    public void update() {
        bg.update();
        handleInput();
    }

    public void draw(Graphics2D g) {
        // bg and title
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dungeon Game", 80, 70); // make function that centers string

        // menu options
        g.setFont(font);
        for (int i = 0; i < buttons.length; i++){
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(buttons[i],145,140+i*15);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            // play
        }
        if (currentChoice == 1) {
            // options
        }
        if (currentChoice == 2) {
            // tutorial
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            select();
        }
        if (Keys.isPressed(Keys.W)) {
            currentChoice--;
            if (currentChoice == -1){
                currentChoice = buttons.length-1;
            }
        }
        if (Keys.isPressed(Keys.S)) {
            currentChoice++;
            if (currentChoice == buttons.length){
                currentChoice = 0;
            }
        }
    }
}
