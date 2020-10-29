package megirai.game;

import biuoop.DrawSurface;
import megirai.interfaces.Sprite;

import java.awt.Image;
import java.awt.Color;

/**
 * BackGround class implements Sprite.
 */
public class BackGround implements Sprite {

    private Image image = null;
    private Color color = null;

    /**
     * Constructor.
     *
     * @param image - gets an image.
     */
    public BackGround(Image image) {
        this.image = image;
    }

    /**
     * Constructor.
     *
     * @param color - gets a color.
     */
    public BackGround(Color color) {
        this.color = color;
    }


    @Override
    public void drawOn(DrawSurface d) {
        if (image != null) {
            d.drawImage(0, 0, image);
        } else if (color != null) {
            d.setColor(color);
            d.fillRectangle(0, 0, 800, 700);
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
