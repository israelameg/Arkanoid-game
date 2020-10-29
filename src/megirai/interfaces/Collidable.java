package megirai.interfaces;

import megirai.generalbehaiors.Velocity;
import megirai.geometry.Ball;
import megirai.geometry.Point;
import megirai.geometry.Rectangle;

/**
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return rect - the current collision.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  - gets the collision point.
     * @param currentVelocity - get the ball's current velocity.
     * @param hitter - the ball that hit the ball.
     * @return currentVelocity - the new velocity after the change.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
