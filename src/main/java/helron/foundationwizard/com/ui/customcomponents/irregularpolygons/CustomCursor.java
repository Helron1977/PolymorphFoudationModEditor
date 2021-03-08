package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.*;
import java.io.IOException;

public class CustomCursor {

    private Cursor cursor ;

    public CustomCursor() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image target = toolkit.getImage(getClass().getResource("/target.png"));
        Point hotspot = new Point(0,0);
        this.cursor = toolkit.createCustomCursor(target, hotspot, "target");

    }

    public Cursor getCursor() {
        return cursor;
    }
}
