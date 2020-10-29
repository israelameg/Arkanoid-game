package megirai.geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;

    /**
     * Constructor - Create a new rectangle with location and width/height.
     *
     * @param upperLeft -
     * @param width     -
     * @param height    -
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        //GUI gui = new GUI("title", 650, 650);
        //DrawSurface d = gui.getDrawSurface();
        //d.setColor(Color.green);
        //d.drawRectangle((int)this.upperLeft.getX(), (int)this.upperLeft.getY(), (int)this.width, (int)this.height);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line - a line
     * @return list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //crete the 4 lines of the rectangle
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line upperL = new Line(upperLeft, upperRight);
        Line downL = new Line(downLeft, downRight);
        Line rigthL = new Line(upperRight, downRight);
        Line leftL = new Line(downLeft, upperLeft);
        //create a list
        List<Point> list = new ArrayList<Point>();

        //add the intersection points to the list
        if (upperL.intersectionWith(line) != null) {
            list.add(upperL.intersectionWith(line));
        }
        if (downL.intersectionWith(line) != null) {
            list.add(downL.intersectionWith(line));
        }
        if (rigthL.intersectionWith(line) != null) {
            list.add(rigthL.intersectionWith(line));
        }
        if (leftL.intersectionWith(line) != null) {
            list.add(leftL.intersectionWith(line));
        }
        return list;
    }

    /**
     * Return the width the rectangle.
     *
     * @return width - the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height the rectangle.
     *
     * @return height - the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upperLeft - the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}
