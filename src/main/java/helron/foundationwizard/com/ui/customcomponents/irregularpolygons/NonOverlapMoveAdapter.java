package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class NonOverlapMoveAdapter extends MoveDrawableMouseListener{

    private Point initialLocation;


    public NonOverlapMoveAdapter(JCanvas canvas) {
        super(canvas);

    }


    public void mouseReleased(MouseEvent e) {
        if(drawable== null) return ;
        try {
            if( !canvas.isAlone(drawable)){
                drawable.setPosition(initialLocation);

            }
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
        initialLocation =null;
        drawable=null;
        canvas.repaint();
    }


    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(drawable!=null) {
            initialLocation=drawable.getPosition();
        }
    }

}