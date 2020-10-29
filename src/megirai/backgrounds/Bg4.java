package megirai.backgrounds;

import biuoop.DrawSurface;
import megirai.game.GameLevel;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Bg4 class - set the background color of level 4.
 */
public class Bg4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color c = new Color(71, 151, 205);
        d.setColor(c);
        d.fillRectangle(0, 0, 700, 700);

        int r = 145;
        int g = 149;
        int b = 149;
        int x;
        int y;
        int radios = 30;


        //draw the clouds by circles
        for (int i = 0; i < 2; i++) {

            // draw the rain by lines
            if (i == 0) {
                x = 150;
                y = 400;
            } else {
                x = 500;
                y = 500;
            }

            d.setColor(Color.white);
            for (int j = 0; j < 7; j++) {
                d.drawLine(x, y, x - 20, 700);
                x = x + 10;
            }

            if (i == 0) {
                x = 200;
                y = 400;
            } else {
                x = 550;
                y = 500;
            }

            c = new Color(192, 192, 192);
            d.setColor(c);
            d.fillCircle(x - 30, y + 15, radios - 7);

            c = new Color(192, 192, 192);
            d.setColor(c);
            d.fillCircle(x - 40, y, radios - 7);

            c = new Color(170, 170, 170);
            d.setColor(c);
            d.fillCircle(x - 20, y - 10, radios - 5);


            c = new Color(160, 160, 160);
            d.setColor(c);
            d.fillCircle(x, y, radios - 5);
            d.fillCircle(x - 10, y + 15, radios - 10);
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
