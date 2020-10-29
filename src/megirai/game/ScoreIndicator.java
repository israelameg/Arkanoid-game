package megirai.game;

import biuoop.DrawSurface;
import megirai.generalbehaiors.Counter;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * ScoreIndicator class - implements megirai.interfaces.Sprite.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score - save the current score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d - gets a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        //create a new rectangle on the upper border.
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 25);
        //draw the score on the the screen.
        d.setColor(Color.black);
        d.drawText(250, 20, "score: " + score.getValue(), 20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * Add the sprite to the game.
     *
     * @param myGame - gets the game.
     */
    public void addToGame(GameLevel myGame) {
        myGame.addSprite(this);
    }

}
