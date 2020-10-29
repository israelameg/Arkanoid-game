package megirai.geometry;

import java.util.List;

/**
 * Line Class - create a line and doing some functions on the line -
 * find the middle point and the intersecting point.
 */
public class Line {

    private Point start;
    private Point end;
    private Point middle;
    private double middleX;
    private double middleY;
    private double length;
    private double slope;
    private double b;
    private double intersectingX;
    private double intersectingY;
    private Point intersectingP;
    private Point closestP;

    /**
     * constructor.
     *
     * @param start - gets the start point.
     * @param end   - gets the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 - gets the x of the start point.
     * @param y1 - gets the y of the start point.
     * @param x2 - gets the x of the end point.
     * @param y2 - gets the y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return length - return the length of the line.
     */
    public double length() {
        length = start.distance(end);
        return length;
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle - returns the middle point of the line
     */
    public Point middle() {
        middleX = (this.start.getX() + this.end.getX()) / 2;
        middleY = (this.start.getY() + this.end.getY()) / 2;
        this.middle = new Point(middleX, middleY);
        return this.middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return start - returns the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end - returns the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Return the slope of the line.
     *
     * @param l - gets a line
     * @return slope - return the slope of the line
     */
    private Double slope(Line l) {
        if ((l.end.getX() - l.start.getX()) != 0) {
            l.slope = (l.end.getY() - l.start.getY()) / (l.end.getX() - l.start.getX());
            return l.slope;
        }
        return null;
    }

    /**
     * Returns the b(intersecting with y-axis).
     *
     * @param l - gets a line
     * @return b - returns the b(intersecting with y-axis)
     */
    private double b(Line l) {
        l.b = -l.slope * l.end.getX() + l.end.getY();
        return l.b;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other - gets a line
     * @return boolean - returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // megirai.apps.check if the lines has the same slope
        if (slope(this) == slope(other)) {
            return false;
        } else {
            // find the x of the intersecting point
            if (slope(this) == null) {
                // if this line is vertical
                this.intersectingX = this.start.getX();
                other.intersectingX = this.start.getX();
                //this.intersectingY = this.slope * this.intersectingX + b(this);
                other.intersectingY = other.slope * other.intersectingX + b(other);
                if (this.start.getY() < this.end.getY()) {
                    if (other.intersectingY > this.end.getY() || other.intersectingY < this.start.getY()) {
                        return false;
                    }
                } else if (other.intersectingY < this.end.getY() || other.intersectingY > this.start.getY()) {
                    return false;
                }
            } else if (slope(other) == null) {
                // if the other line is vertical
                this.intersectingX = other.start.getX();
                other.intersectingX = other.start.getX();
                this.intersectingY = this.slope * this.intersectingX + b(this);
                // other.intersectingY = other.slope * other.intersectingX + b(other);
                if (other.start.getY() < other.end.getY()) {
                    if (this.intersectingY > other.end.getY() || this.intersectingY < other.start.getY()) {
                        return false;
                    }
                } else if (this.intersectingY < other.end.getY() || this.intersectingY > other.start.getY()) {
                    return false;
                }
            } else {
                this.intersectingX = (b(this) - b(other)) / (slope(other) - slope(this));
                other.intersectingX = this.intersectingX;
            }
            if (this.start.getX() < this.end.getX()) {
                if (this.start.getX() > this.intersectingX || this.intersectingX > this.end.getX()) {
                    return false;
                }
            } else {
                if (this.start.getX() < this.intersectingX || this.intersectingX < this.end.getX()) {
                    return false;
                }
            }
            if (other.start.getX() < other.end.getX()) {
                if (other.start.getX() > other.intersectingX || other.intersectingX > other.end.getX()) {
                    return false;
                }
            } else {
                if (other.start.getX() < other.intersectingX || other.intersectingX < other.end.getX()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other - gets a line
     * @return point - returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        boolean isInter = this.isIntersecting(other);
        if (isInter) {
            if (slope(this) == null) {
                this.intersectingY = other.slope * other.intersectingX + b(other);
            } else {
                this.intersectingY = this.slope * this.intersectingX + b(this);
            }
            this.intersectingP = new Point(this.intersectingX, this.intersectingY);
            return this.intersectingP;
        }
        return null;
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other - gets a line
     * @return bool - return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect - a rectangle
     * @return p - the closest intersection point to the start of the line or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.size() == 0) {
            return null;
        }
        closestP = list.get(0);
        for (Point p : list) {
            if (p.getX() - this.start.getX() < closestP.getX() - this.start.getX()) {
                closestP = p;
            }
        }
        return closestP;
    }
}
