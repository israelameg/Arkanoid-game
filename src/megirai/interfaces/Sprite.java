package megirai.interfaces;

import biuoop.DrawSurface;
import megirai.game.GameLevel;

/**
 * Sprite interface.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d - gets a DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add the sprite to the game.
     *
     * @param myGame - gets the game.
     */
    void addToGame(GameLevel myGame);

}
