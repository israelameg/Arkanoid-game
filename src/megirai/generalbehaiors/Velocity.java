package megirai.generalbehaiors;

import megirai.geometry.Point;

/**
 * megirai.generalbehaiors.Velocity.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx - gets the dx.
     * @param dy - gets the dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the angle and speed and change the dx and dy.
     *
     * @param angle - gets the angle.
     * @param speed - gets the speed.
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -1 * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p - gets the current point.
     * @return newPoint - a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }

    /**
     * accessors.
     *
     * @return dx - the change on the X axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessors.
     *
     * @return dy - The change on the Y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * accessors.
     *
     * @param dySet - The change on the Y axis.
     */
    public void setDy(double dySet) {
        this.dy = dySet;
    }

    /**
     * accessors.
     *
     * @param dxSet - The change on the Y axis.
     */
    public void setDx(double dxSet) {
        this.dx = dxSet;
    }
}
