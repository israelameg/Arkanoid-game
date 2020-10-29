package megirai.backgrounds;

import biuoop.DrawSurface;
import megirai.game.GameLevel;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Bg1 class - set the background color of level 1.
 */
public class Bg1 implements Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d - gets a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 700, 700);
        d.setColor(Color.blue);
        d.drawLine(350, 60, 350, 140);
        d.drawLine(350, 200, 350, 280);
        d.drawLine(380, 170, 460, 170);
        d.drawLine(230, 170, 320, 170);
        int radios = 100;
        for (int i = 0; i < 3; i++) {
            d.drawCircle(350, 170, radios);
            radios = radios - 20;
        }
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
