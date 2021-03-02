package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.event.MouseEvent;
import java.util.List;

public class MoveDrawableMouseListener extends JCanvasMouseAdapter {

    protected MovableDrawable drawable;


    public MoveDrawableMouseListener(JCanvas canvas) {
        super(canvas);
    }


    public void mouseDragged(MouseEvent e) {
        if (drawable != null) {
            drawable.setPosition(e.getPoint());
            canvas.repaint();
        }
    }


    public void mousePressed(MouseEvent e) {
        List selectedDrawables = null;
        try {
            selectedDrawables = canvas.findDrawables(e.getPoint());
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
        if (selectedDrawables.size() == 0)
            return;
        drawable = (MovableDrawable) selectedDrawables.get(0);
    }

    public void mouseReleased(MouseEvent e) {
        drawable = null;
    }

}