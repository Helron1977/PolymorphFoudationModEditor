package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.*;

public abstract class FormDrawable implements MovableDrawable{
    protected Rectangle rectangle;
    protected Color color;

    public FormDrawable(Color color, Point pos, Dimension dim) {
        this.color = color;
        this.rectangle = new Rectangle(pos, dim);
    }


    public abstract void draw(Graphics graphic);

    public Rectangle getRectangle() {
        return (Rectangle) rectangle.clone();
    }

    public Point getPosition() {
        Point point = rectangle.getLocation();
        point.x = point.x + (rectangle.width/2);
        point.y = point.y + (rectangle.width/2);
        return point;
    }

    public void setPosition(Point point) {
        rectangle.x = (point.x+ rectangle.width/2);
        rectangle.y = (point.y + rectangle.width/2);
    }
}
