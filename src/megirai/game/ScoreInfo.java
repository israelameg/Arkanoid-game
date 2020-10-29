package megirai.game;

import java.io.Serializable;

/**
 * ScoreInfo class implements Serializable.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Constructor.
     *
     * @param name  - gets the player name.
     * @param score - gets the player score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Return the player name.
     *
     * @return the player name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the player score.
     *
     * @return the player score.
     */
    public int getScore() {
        return this.score;
    }
}
