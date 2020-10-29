package megirai.geometry;

/**
 * Point Class - create a point and do some functions -
 * check if points are equals and calculate the distance between two points.
 *
 */
public class Point {
    private double x;
    private double y;
    private double dist;

    /**
     * constructor.
     *
     * @param x - gets the x of the point.
     * @param y - gets the y of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor.
     *
     * @param p - gets the point.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Return the distance of this point to the other point.
     *
     * @param other - gets an other point.
     * @return dist - return the distance of this point to the other point.
     */
    public double distance(Point other) {
        this.dist = Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
        return dist;
    }

    /**
     * Return true if the points are equal, false otherwise.
     *
     * @param other - gets an other point.
     * @return boolean - return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }

    /**
     * Return the x value of this point.
     *
     * @return x - return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return y - return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the x value of this point.
     *
     * @param otherX - the x value of this point.
     */
    public void setX(double otherX) {
        this.x = otherX;
    }

    /**
     * Set the y value of this point.
     *
     * @param otherY - the y value of this point.
     */
    public void setY(double otherY) {
        this.y = otherY;
    }
}

