package megirai.game;

import biuoop.DrawSurface;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * LevelNameIndicator class implements Sprite.
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;

    /**
     * Constructor.
     *
     * @param levelName - the level name.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d - gets a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        //draw the num of lives on the the screen.
        d.drawText(450, 20, "Levels Name: " + levelName, 20);
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
