package megirai.game;

import biuoop.DrawSurface;
import megirai.interfaces.Sprite;

import java.awt.Image;
import java.awt.Color;

/**
 * FillBlock class implements sprite.
 */
public class FillBlock implements Sprite {

    private Color color;
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;

    /**
     * Constructor.
     *
     * @param color - gets a color.
     */
    public FillBlock(Color color) {
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param image - gets an image.
     */
    public FillBlock(Image image) {
        this.image = image;
    }

    /**
     * Return the block's color.
     *
     * @return the block's color.
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(color);
            d.fillRectangle(x, y, this.width, this.height);
        }

        if (image != null) {
            d.drawImage(x, y, image);
        }

    }

    /**
     * Set the x position.
     *
     * @param xPos - gets the x position.
     */
    public void setX(int xPos) {
        this.x = xPos;
    }

    /**
     * Set the y position.
     *
     * @param yPos - gets the y position.
     */
    public void setY(int yPos) {
        this.y = yPos;
    }

    /**
     * Return the x position.
     *
     * @return the x position.
     */
    public int getX() {
        return x;
    }

    /**
     * Return the y position.
     *
     * @return the y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the block's height.
     *
     * @param h - gets the block's height.
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Set the the block's width.
     *
     * @param w - the block's width.
     */
    public void setWidth(int w) {
        this.width = w;
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel myGame) {
        myGame.addSprite(this);
    }
}
