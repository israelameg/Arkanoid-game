package megirai.game;

import megirai.generalbehaiors.CollisionInfo;
import megirai.geometry.Line;
import megirai.geometry.Point;
import megirai.interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * GameLevel environment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove the given collidable from the environment.
     *
     * @param c - a collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - how the ball will move
     *                   without any obstacles.
     * @return closestCollision - the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> collisonList = new ArrayList();
        for (Collidable c : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                CollisionInfo currentCollision = new CollisionInfo();
                Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                currentCollision.setCollisionPoint(collisionPoint);
                currentCollision.setCollidable(c);
                collisonList.add(currentCollision);
            }
        }

        if (collisonList.size() == 0) {
            return null;
        }

        double distance1 = collisonList.get(0).getCollisionPoint().distance(trajectory.start());
        double distance2;
        CollisionInfo closestCollision = collisonList.get(0);
        for (CollisionInfo c : collisonList) {
            distance2 = c.getCollisionPoint().distance(trajectory.start());
            if (distance1 == distance2 || distance1 > distance2) {
                if (distance1 == distance2) {
                    if (Math.abs(closestCollision.getCollisionPoint().getX() - trajectory.start().getX())
                            > Math.abs(collisonList.get(0).getCollisionPoint().getX() - trajectory.start().getX())) {
                        closestCollision = c;
                        distance1 = distance2;
                    }
                } else {
                    closestCollision = c;
                    distance1 = distance2;
                }
            }

        }

        return closestCollision;
    }
}
