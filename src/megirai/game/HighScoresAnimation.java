package megirai.game;

import biuoop.DrawSurface;
import megirai.interfaces.Animation;

import java.awt.Color;

/**
 * HighScoresAnimation class implements Animation.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scoresTable;

    /**
     * Constructor.
     *
     * @param scores - gets an HighScoresTable object.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scoresTable = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int y = 100;
        d.setColor(Color.red);
        d.drawText(20, 40, "High Scores", 32);
        d.setColor(Color.green);
        d.drawText(50, y, "Player Name", 25);
        d.drawText(400, y, "Score", 25);
        d.setColor(Color.magenta);
        for (ScoreInfo s : scoresTable.getHighScores()) {
            y = y + 50;
            d.drawText(50, y, s.getName(), 25);
            d.drawText(400, y, String.valueOf(s.getScore()), 25);
        }
        d.setColor(Color.blue);
        d.drawText(150, 550, "Press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
