package GameState;

import Handler.Keys;
import Images.Background;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardState extends GameState {

    private Background bg;
    private static ArrayList<Score> scores;

    private Color font_color;
    private Font font;

    public LeaderboardState(GameStateManager gsm) {
        this.gsm = gsm;
        scores = new ArrayList<>();

        font_color = new Color(236, 239, 233);
        font = new Font("Century Gothic", Font.PLAIN, 30);

        try{
            bg = new Background("/Assets/leaderboard.png", 0);
            bg.setVector(0, 0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init() {
        scores.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/GameState/highscores.txt"));

            String line = br.readLine();

            while (line != null) {
                if (!(line.trim().equals(""))) {
                    String[] parts = line.split("&");
                    scores.add(new Score(Integer.parseInt(parts[0]), parts[1]));
                }
                line = br.readLine();
            } br.close();
            Collections.reverse(scores); // newer dates priority
            Collections.sort(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(int score) {}

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(font_color);
        g.setFont(font);

        int count = Math.min(scores.size(), 10);
        for (int i = 0; i < count; i++){ // draw scores
            g.drawString((i+1) +  ". " + scores.get(i).getScore() + " points | " +  scores.get(i).getDate(),300,200+(50*i));
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

    // class for sorting high scores
    class Score implements Comparable<Score> {
        private int score;
        private String date;

        public Score(int score, String date) {
            this.score = score;
            this.date = date;
        }

        public int getScore() { return score; }
        public String getDate() { return date; }

        public int compareTo(Score obj) { // determines which score is larger
            int other_score = obj.getScore();
            return other_score - this.score;
        }
    }
}
