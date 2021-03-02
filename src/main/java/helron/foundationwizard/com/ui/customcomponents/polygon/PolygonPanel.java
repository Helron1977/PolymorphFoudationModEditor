package helron.foundationwizard.com.ui.customcomponents.polygon;

import javax.swing.*;
import java.awt.*;


public class PolygonPanel extends JPanel {
    Polygon polygon;
    int sidesNb;
    double alpha;
    double mutiplicator;
    StringBuilder coordinates;

    public JPanel affiche(int sidesNb, Double alpha, Double multiplicator){
        this.sidesNb = sidesNb;
        this.alpha = alpha;
        this.mutiplicator = multiplicator;
        this.polygon = new Polygon();
        this.coordinates = new StringBuilder();

        this.setBounds(10,10,300,300);
        this.setPreferredSize(new Dimension(200,200));
        return this;
    }
        

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            initPolygon();

            g.drawPolygon(polygon);
        }

    private void initPolygon() {
        for (int i = 0; i < sidesNb; i++) polygon.addPoint((int) (
                100 + 10 * mutiplicator * Math.cos(alpha+(i * 2 * Math.PI / sidesNb))),(int) (
                100 + 10 * mutiplicator * Math.sin(alpha+((i * 2 * Math.PI / sidesNb)))));

        int[] xCoordinates = polygon.xpoints;
        int[] yCoordonites = polygon.ypoints;


        for (int i = 0; i < polygon.npoints; i++){
            coordinates.append(xCoordinates[i] - 100)
                    .append(",")
                    .append(yCoordonites[i] - 100);
        }
    }


    public StringBuilder getCoordinates() {
        return coordinates;
    }
}
