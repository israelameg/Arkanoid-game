package megirai.backgrounds;

import biuoop.DrawSurface;
import megirai.game.GameLevel;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Bg3 class - set the background color of level 3.
 */
public class Bg3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Color c = new Color(67, 159, 93);
        d.setColor(c);
        d.fillRectangle(0, 0, 700, 700);
        d.setColor(Color.white);
        d.fillRectangle(50, 450, 100, 200);

        int lineY = 450;
        int hegiht = 15;
        for (int i = 0; i < 5; i++) {
            d.setColor(Color.black);
            d.fillRectangle(50, lineY, 100, hegiht);
            hegiht = 10;
            lineY = lineY + 32;
        }

        d.fillRectangle(50, 450, 15, 200);
        d.fillRectangle(150, 450, 15, 200);

        d.setColor(Color.darkGray);
        d.fillRectangle(90, 390, 30, 60);
        d.fillRectangle(100, 250, 10, 140);

        //circles
        d.setColor(Color.orange);
        d.fillCircle(105, 250, 15);
        d.setColor(Color.red);
        d.fillCircle(105, 250, 10);
        d.setColor(Color.white);
        d.fillCircle(105, 250, 5);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel myGame) {
        myGame.addSprite(this);
    }
}
