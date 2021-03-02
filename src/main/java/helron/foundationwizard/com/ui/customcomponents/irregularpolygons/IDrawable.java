package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.*;

public interface IDrawable {

    public void draw(Graphics graphics);

    public Rectangle getRectangle() throws CloneNotSupportedException;
}
