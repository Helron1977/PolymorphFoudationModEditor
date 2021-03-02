package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.*;

public class RectangleDrawable extends FormDrawable{

    public RectangleDrawable(Color color, Point pos, Dimension dim) {
        super(color, pos, dim);
    }


    @Override
    public void draw(Graphics graphic) {
        Color c = graphic.getColor();
        graphic.setColor(color);
        graphic.fillRect(rectangle.x, rectangle.y, rectangle.height, rectangle.width);
        graphic.setColor(c);
    }
}
