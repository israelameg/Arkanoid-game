package megirai.game;

import biuoop.DrawSurface;
import megirai.generalbehaiors.Counter;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * LivesIndicator class - implements megirai.interfaces.Sprite.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor.
     *
     * @param numberOfLives - counter of lives left.
     */
    public LivesIndicator(Counter numberOfLives) {
        this.lives = numberOfLives;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d - gets a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        //draw the num of lives on the the screen.
        d.drawText(50, 20, "lives: " + lives.getValue(), 20);
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
