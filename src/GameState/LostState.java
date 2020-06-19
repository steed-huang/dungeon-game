package GameState;

import Handler.Keys;
import Images.Background;

import java.awt.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LostState extends GameState {

    private Background bg;
    private Color font_color;
    private Font font;

    private int score;
    private String date;

    private static DateTimeFormatter DATE_TIME_FORMATTER;

    public LostState(GameStateManager gsm) {
        this.gsm = gsm;

        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        font_color = new Color(236, 239, 233);
        font = new Font("Century Gothic", Font.PLAIN, 50);

        score = -1;

        try{
            bg = new Background("/Assets/death.png", 0);
            bg.setVector(0, 0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init() {}
    public void init(int score) {
        this.score = score;
        this.date = ZonedDateTime.now().format(DATE_TIME_FORMATTER);

        try {
            FileWriter writer = new FileWriter("src/GameState/highscores.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // filewriter is faster for a single line write but bufferedwriter was quicker to implement
            bufferedWriter.newLine();
            bufferedWriter.write(this.score+"&"+this.date);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(font_color);
        g.setFont(font);
        g.drawString("Score: " + score + " points",310,580);
        g.drawString("Date: " + date,310,640);
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            gsm.setState(GameStateManager.MENU);
        }
    }
}
