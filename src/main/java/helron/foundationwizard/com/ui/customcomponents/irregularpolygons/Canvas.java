package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import helron.foundationwizard.com.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    List<Shape> drawables = new ArrayList<>();
    Image cursor;
    private Point2D cursorLocation = new Point2D.Float();
    List<Point2D.Float> clickedPoints = new ArrayList<>();

    public Canvas() throws IOException {
        Cursor empty = new CustomCursor().getCursor();
        setCursor(empty);
        this.cursor = ImageIO.read(Main.class.getResource("/target.png"));
    }

    public void paintComponent (Graphics g) {
        Graphics2D canvas = (Graphics2D) g.create();
        super.paintComponent(canvas);
        if (getMousePosition() !=null) {

            float mousePositionX = (getMousePosition().getLocation().x -200)/40f;
            float mousePositiony = (-getMousePosition().getLocation().y+200)/40f;
            canvas.drawString(mousePositionX+" "+mousePositiony, 10, 410);
        }


        for(int i =0; i < 400 ; i += 40) {
            float[] motif = {2, 4, 4, 4, 2 };

            if ((i/40)%2 != 0)
                canvas.setStroke(
                        new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,15, motif,0
                        )
                );
            else
                canvas.setStroke(new BasicStroke(0.3f));

            canvas.setPaint(Color.lightGray);
            if (i==200) {
                canvas.setPaint(Color.RED);
                canvas.setStroke(new BasicStroke(0.3f));
            }
            canvas.drawLine(i, 0, i,400);
            if (i==200) {
                canvas.setPaint(Color.GREEN);
                canvas.setStroke(new BasicStroke(0.3f));
            }
            canvas.drawLine(0, i, 400, i);
        }




        for (Shape drawable : drawables) {
            canvas.setColor(new Color(255, 255, 255, 153));
            canvas.setStroke(new BasicStroke(3));
            canvas.fill(drawable);
        }
        canvas.drawImage(cursor,(int) cursorLocation.getX(),(int) cursorLocation.getY(),this );

    }

    public void setCursorLocation(Point2D cursorLocation) {
        this.cursorLocation = cursorLocation;
    }

    public void addDrawable(Shape drawable){
        drawables.add(drawable);
        repaint();
    }

    public void removeDrawable( Shape drawable){
        drawables.remove(drawable);

    }

    public void clearDrawables() {
        drawables.clear();
        repaint();
    }

    public List<Shape> getDrawables() {
        return drawables;
    }

    public void addPoints(Point2D.Float point) {
        clickedPoints.add(point);
    }

    public List<Point2D.Float> getClickedPoints() {
        return clickedPoints;
    }
}
