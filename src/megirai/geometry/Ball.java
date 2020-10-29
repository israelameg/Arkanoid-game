package megirai.geometry;

import biuoop.DrawSurface;
import megirai.game.GameLevel;
import megirai.game.GameEnvironment;
import megirai.generalbehaiors.CollisionInfo;
import megirai.generalbehaiors.Velocity;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Ball Class.
 */
public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point maxBorder;
    private Point minBorder;
    private GameEnvironment game;

    /**
     * constructor.
     *
     * @param center - gets the center point of the ball.
     * @param r      - gets the ball's radious.
     * @param color  - gets the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.maxBorder = new Point(200, 200);
        this.minBorder = new Point(0, 0);
    }

    /**
     * constructor.
     *
     * @param x     - gets the x of the center point.
     * @param y     - gets the y of the center point.
     * @param r     - gets the ball's radious.
     * @param color - gets the color of the ball.
     * @param game  - gets the game environment.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.maxBorder = new Point(200, 200);
        this.minBorder = new Point(0, 0);
        this.game = game;
    }

    /**
     * constructor.
     *
     * @param x     - gets the x of the center point.
     * @param y     - gets the y of the center point.
     * @param r     - gets the ball's radious.
     * @param color - gets the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.maxBorder = new Point(200, 200);
        this.minBorder = new Point(0, 0);
    }

    /**
     * constructor.
     *
     * @param x         - gets the x of the center point.
     * @param y         - gets the y of the center point.
     * @param r         - gets the ball's radious.
     * @param color     - gets the color of the ball.
     * @param maxBorder - gets the max borders.
     * @param minBorder - gets the min borders.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Point minBorder, Point maxBorder) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.maxBorder = maxBorder;
        this.minBorder = minBorder;
    }

    /**
     * accessors.
     *
     * @return x - the point x of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessors.
     *
     * @return y - the point y of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessors.
     *
     * @return r - the radious of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessors.
     *
     * @return color - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface - gets the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);

    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel myGame) {
        myGame.addSprite(this);
    }

    /**
     * Set the  velocity.
     *
     * @param otherV - gets the velocity.
     */
    public void setVelocity(Velocity otherV) {
        this.v = otherV;
    }

    /**
     * Set the velocity.
     *
     * @param dx - gets the dx.
     * @param dy - gets the dy.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Return the velocity.
     *
     * @return v - the velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Move the ball and checks if it is not out of the border.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy());

        if (game.getClosestCollision(trajectory) != null) {
            if (this.getVelocity().getDx() <= 0) {
                this.center.setX(game.getClosestCollision(trajectory).getCollisionPoint().getX()
                        + (this.r / Math.sqrt(2)));
            } else {
                this.center.setX(game.getClosestCollision(trajectory).getCollisionPoint().getX()
                        - (this.r / Math.sqrt(2)));
            }

            if (this.getVelocity().getDy() <= 0) {
                this.center.setY(game.getClosestCollision(trajectory).getCollisionPoint().getY()
                        + (this.r / Math.sqrt(2)));
            } else {
                this.center.setY(game.getClosestCollision(trajectory).getCollisionPoint().getY()
                        - (this.r / Math.sqrt(2)));
            }

            CollisionInfo c = game.getClosestCollision(trajectory);
            this.setVelocity(c.getCollidable().hit(this, c.getCollisionPoint(), this.getVelocity()));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Remove the ball from the game.
     *
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.getBallCounter().decrease(1);
    }
}
