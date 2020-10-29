package megirai.geometry;

import biuoop.DrawSurface;
import megirai.game.FillBlock;
import megirai.game.GameLevel;
import megirai.generalbehaiors.Velocity;
import megirai.interfaces.Collidable;
import megirai.interfaces.HitListener;
import megirai.interfaces.HitNotifier;
import megirai.interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color color;
    private int lives;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Map<String, FillBlock> bgBlock;
    private Color strokeColor;

    /**
     * Constructor.
     *
     * @param rect - gets a rectangle
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Constructor - Create a new rectangle with location and width/height.
     *
     * @param upperLeftX - gets the x of the upper left point.
     * @param upperLeftY - gets the x of the upper left point.
     * @param width      - gets the rectangle's width.
     * @param height     - gets the rectangle's height.
     * @param color      - gets the rectangle's color.
     * @param text       - gets the number of hit-points.
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, java.awt.Color color, int text) {
        this.color = color;
        Point upperLeft = new Point(upperLeftX, upperLeftY);
        this.rect = new Rectangle(upperLeft, width, height);
        this.lives = text;
    }

    /**
     * Constructor - Create a new rectangle with location and width/height.
     *
     * @param upperLeftX - gets the x of the upper left point.
     * @param upperLeftY - gets the x of the upper left point.
     * @param width      - gets the rectangle's width.
     * @param height     - gets the rectangle's height.
     * @param lives      - gets the block's lives.
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, int lives) {
        Point upperLeft = new Point(upperLeftX, upperLeftY);
        this.rect = new Rectangle(upperLeft, width, height);
        this.lives = lives;
    }

    /**
     * Return the current collision ( a rectangle).
     *
     * @return rect - the current collision.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Change the velocity and return the new velocity.
     *
     * @param collisionPoint  - gets the collision point
     * @param currentVelocity - get the ball's current velocity
     * @param hitter          - the ball that hit the ball.
     * @return currentVelocity - the new velocity after the change.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            currentVelocity.setDy(-1 * currentVelocity.getDy());
        } else if (collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
            currentVelocity.setDx(-1 * currentVelocity.getDx());
        }
        this.lives--;
        // update the Collidable interface accordingly.
        this.notifyHit(hitter);

        return currentVelocity;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface - gets the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {

        if (bgBlock != null) {
            //System.out.println("bg Block Size = " + bgBlock.size());
            for (String s : bgBlock.keySet()) {
                if (Integer.parseInt(s) == lives) {
                    bgBlock.get(s).setX((int) this.rect.getUpperLeft().getX());
                    bgBlock.get(s).setY((int) this.rect.getUpperLeft().getY());
                    bgBlock.get(s).setHeight((int) this.rect.getHeight());
                    bgBlock.get(s).setWidth((int) this.rect.getWidth());
                    bgBlock.get(s).drawOn(surface);
                }
            }
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
            surface.setColor(Color.black);
        }

        if (strokeColor != null) {
            surface.setColor(strokeColor);
            surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
        /*surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());*/



      /*  if(image != null){
            surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),image);
            System.out.println("image");
        } else if (color != null){
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
            surface.setColor(Color.black);
        }*/


    }

      /*  if (this.lives < 0) {
            surface.drawText((int) this.rect.getUpperLeft().getX() + 20, (int) this.rect.getUpperLeft().getY() + 25,
                    "X", 10);
        } else {
            surface.drawText((int) this.rect.getUpperLeft().getX() + 20, (int) this.rect.getUpperLeft().getY() + 25,
                    "" + this.lives, 10);
        }*/

    @Override
    /**
     * do nothing.
     */
    public void timePassed() {
        //what to do?
    }

    @Override
    /**
     * Add the block to the the collidable list and the sprite list.
     * @param myGame - gets the game.
     */
    public void addToGame(GameLevel myGame) {
        myGame.addCollidable(this);
        myGame.addSprite(this);
    }


    /**
     * Remove the block from collidable list and the sprite list.
     *
     * @param game - gets the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
        game.getBlockCounter().decrease(1);
    }

    @Override
    /**
     * Add hl as a listener to hitListeners list.
     *
     * @param hl - an megirai.interfaces.HitListener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - an megirai.interfaces.HitListener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifiers all of the registered megirai.interfaces.HitListener objects by calling their hitEvent method.
     *
     * @param hitter - the megirai.geometry.Ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Return the number of hit-points that left.
     *
     * @return lives - the number of hit-points that left.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Set the color.
     *
     * @param c - gets the color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Set the block's backGround.
     *
     * @param bg - gets block's backGround.
     */
    public void setBgBlock(Map<String, FillBlock> bg) {
        this.bgBlock = bg;
    }

    /**
     * Set the stroke color.
     *
     * @param stroke - gets the stroke color.
     */
    public void setStrokeColor(Color stroke) {
        this.strokeColor = stroke;
    }
}
