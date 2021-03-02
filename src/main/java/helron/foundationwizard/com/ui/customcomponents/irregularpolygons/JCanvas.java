package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import helron.foundationwizard.com.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JCanvas extends JPanel {
    InputStream grind = Main.class.getResourceAsStream("/grille.PNG");
    Image backGround = ImageIO.read(grind);

    private final List<IDrawable> drawables = new LinkedList<>();

    public JCanvas() throws IOException {
    }

    public void paint(Graphics g) {
        super.paint(g);
        int width = backGround.getWidth(this);
        int height = backGround.getHeight(this);
        int x=((this.getWidth()-width)/2);
        int y=((this.getHeight()-height)/2);

        g.drawImage(backGround, x, y, width, height, this);

        for (IDrawable component : drawables ) {
            component.draw(g);
        }

    }



    public void addDrawable(IDrawable component) {
        drawables.add(component);
        repaint();
    }

    public void removeDrawable(IDrawable component) {
        drawables.remove(component);
        repaint();
    }

    public void clear(){
        drawables.clear();
        repaint();
    }

    public List<IDrawable> findDrawables(Point p) throws CloneNotSupportedException {
        List<IDrawable> listDrawableForms = new ArrayList<>();
        for ( IDrawable component : drawables ) {
            if(component.getRectangle().contains(p)){
                listDrawableForms.add(component);
            }
        }
        return listDrawableForms;
    }
    public boolean isFree(Rectangle rect) throws CloneNotSupportedException {
        for ( IDrawable component : drawables ) {
            if(component.getRectangle().intersects(rect)){
                return false;
            }
        }
        return true;
    }

    public boolean isAlone(IDrawable drawable) throws CloneNotSupportedException {
        Rectangle rect = drawable.getRectangle();
        for ( IDrawable component : drawables ) {
            if(!component.equals(drawable) && component.getRectangle().intersects(rect)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        JCanvas jc = new JCanvas();

        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(400, 200));
        Dimension dim = new Dimension(40, 40);
        IDrawable rect = new RectangleDrawable(Color.RED, new Point(0, 0),dim);
        jc.addDrawable(rect);

        new MoveDrawableMouseListener(jc);
        GUIHelper.showOnFrame(jc, "test JCanvas");
    }

}
