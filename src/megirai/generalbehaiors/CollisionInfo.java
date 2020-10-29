package megirai.generalbehaiors;

import megirai.geometry.Point;
import megirai.interfaces.Collidable;

/**
 * megirai.generalbehaiors.CollisionInfo class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Return the point at which the collision occurs.
     *
     * @return collisionPoint - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Return the collidable object involved in the collision.
     *
     * @return collidable - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

    /**
     * Return the collision point.
     *
     * @return collisionPoint.
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }

    /**
     * Set the collision point.
     *
     * @param collisionP -  gets the collision megirai.geometry.Point.
     */
    public void setCollisionPoint(Point collisionP) {
        this.collisionPoint = collisionP;
    }

    /**
     * Return the collidable.
     *
     * @return collidable - the current collidable.
     */
    public Collidable getCollidable() {
        return this.collidable;
    }

    /**
     * Set the collidable.
     *
     * @param c - gets the collidable.
     */
    public void setCollidable(Collidable c) {
        this.collidable = c;
    }
}
