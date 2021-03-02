package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.event.*;
import javax.swing.SwingUtilities;

public abstract class JCanvasMouseListener extends MouseAdapter {

    protected JCanvas canvas;

    public JCanvasMouseListener(JCanvas canvas) {
        super();
        this.canvas = canvas;
        canvas.addMouseListener(this);
    }

    public JCanvas getCanvas() {
        return canvas;
    }

    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            try {
                leftClickAction(e);
            } catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
            }
        } else {
            try {
                rightClickAction(e);
            } catch (CloneNotSupportedException cloneNotSupportedException) {
                cloneNotSupportedException.printStackTrace();
            }
        }
    }


    protected void rightClickAction(MouseEvent e) throws CloneNotSupportedException {

    }

    protected void leftClickAction(MouseEvent e) throws CloneNotSupportedException {

    }

}
