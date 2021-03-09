package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class PolygonMouseCreator extends MouseAdapter {
    protected Canvas canvas;
    GeneralPath.Float polygon = new GeneralPath.Float();
    float SCALE = 40f;


    public PolygonMouseCreator(Canvas canvas) {
        this.canvas = canvas;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {
            leftClickAction(e);
        } else {
            rightClickAction(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        canvas.setCursorLocation(new Point2D.Float(e.getX()-40, e.getY()-40));
        canvas.repaint();
    }

    private void leftClickAction(MouseEvent e) {

        if (polygon.getCurrentPoint()==null) {
            polygon.moveTo(e.getX(), e.getY());
        }
        polygon.lineTo(e.getX(),e.getY());

        Ellipse2D.Double dot = new Ellipse2D.Double(e.getX(),e.getY(),3,3);

        Point2D.Float clickedPoint = new Point2D.Float(
                (e.getX()-200)/40,
                (-e.getY()+200f)/40
//                SCALE*1/(e.getX()-200f),
//                SCALE*1/((e.getY()-200f)-((e.getY()-200f))*2f));
                );
        canvas.addPoints( clickedPoint);
        canvas.addDrawable(dot);


    }

    private void rightClickAction(MouseEvent e) {
        polygon.closePath();
        canvas.addDrawable((Shape) polygon.clone());
        polygon.reset();
    }


}
