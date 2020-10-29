package megirai.backgrounds;

import biuoop.DrawSurface;
import megirai.game.GameLevel;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Bg2 class - set the background color of level 2.
 */
public class Bg2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        //create s sun with circle and lines
        d.setColor(Color.getHSBColor(54, 91, 22));
        d.fillCircle(100, 150, 60);
        d.setColor(Color.getHSBColor(58, 91, 21));
        d.fillCircle(100, 150, 50);

        int lineX = 0;
        for (int i = 0; i < 100; i++) {
            d.drawLine(100, 150, lineX, 250);
            lineX = lineX + 7;
        }


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel myGame) {
        myGame.addSprite(this);
    }
}
