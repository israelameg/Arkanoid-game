package megirai.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import megirai.generalbehaiors.Velocity;
import megirai.geometry.Ball;
import megirai.geometry.Point;
import megirai.geometry.Rectangle;
import megirai.interfaces.Collidable;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Point upperLeft;
    private int paddleSpeed;

    /**
     * Constructor - create a new rectangle with location and width/height.
     *
     * @param upperLeftX - gets the x of the upper left point.
     * @param upperLeftY - gets the Y of the upper left point.
     * @param width      - gets the rectangle's width.
     * @param height     - gets the rectangle's height.
     * @param ks        - gets the key board.
     * @param pSpeed - gets the paddle paddleSpeed.
     */
    public Paddle(double upperLeftX, double upperLeftY, double width, double height, KeyboardSensor ks, int pSpeed) {
        upperLeft = new Point(upperLeftX, upperLeftY);
        this.rect = new Rectangle(upperLeft, width, height);
        keyboard = ks;
        this.paddleSpeed = pSpeed;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() - paddleSpeed <= 20) {
            Point newUp = new Point(20, this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUp, this.rect.getWidth(), this.rect.getHeight());
            //this.rect.upperLeft.setX(0);
        } else {
            Point newUp = new Point(this.rect.getUpperLeft().getX() - paddleSpeed, this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUp, this.rect.getWidth(), this.rect.getHeight());
            //this.rect.upperLeft.setX(this.rect.upperLeft.getX() - paddleSpeed);
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth() + paddleSpeed >= 780) {
            Point newUp = new Point(780 -  this.rect.getWidth(), this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUp, this.rect.getWidth(), this.rect.getHeight());
            //this.rect.upperLeft.setX(450);
        } else {
            Point newUp = new Point(this.rect.getUpperLeft().getX() + paddleSpeed, this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUp, this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * Check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle.
     *
     * @param d - gets the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
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
     * The paddle has 5 equally-spaced regions and the velocity change accordingly.
     *
     * @param collisionPoint  - gets the collision point
     * @param currentVelocity - get the ball's current velocity
     * @param hitter - the ball that hit the block.
     * @return currentVelocity - the new velocity after the change.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double region1 = this.rect.getUpperLeft().getX() + this.rect.getWidth() / 5;
        double region2 = this.rect.getUpperLeft().getX() + 2 * this.rect.getWidth() / 5;
        double region3 = this.rect.getUpperLeft().getX() + 3 * this.rect.getWidth() / 5;
        double region4 = this.rect.getUpperLeft().getX() + 4 * this.rect.getWidth() / 5;
        double region5 = this.rect.getUpperLeft().getX() + this.rect.getWidth();

        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (collisionPoint.getX() < region1) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPoint.getX() < region2) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPoint.getX() < region3) {
            currentVelocity = Velocity.fromAngleAndSpeed(0, speed);
        } else if (collisionPoint.getX() < region4) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
        } else if (collisionPoint.getX() < region5) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
        }

        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - gets the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Return the paddle's rectangle.
     *
     * @return rect - the paddle's rectangle.
     */
    public Rectangle getRect() {
        return this.rect;
    }
}
