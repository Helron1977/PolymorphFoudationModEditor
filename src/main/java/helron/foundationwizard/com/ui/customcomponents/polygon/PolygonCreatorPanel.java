package helron.foundationwizard.com.ui.customcomponents.polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class PolygonCreatorPanel extends JPanel {
    private JPanel rightPanel;
    private JPanel leftPanel;
    private  JTextPane southPanel;
    List<JSpinner> settingSpinners = new ArrayList<JSpinner>();
    private int sideNb;
    private double angle;
    private double factor;
    StringBuilder coordinates;
    
    public PolygonCreatorPanel() {
        this.rightPanel = new JPanel();
        this.leftPanel = new JPanel();
        this.southPanel = new JTextPane();

        init();
    }

    private void init() {
        LayoutManager layoutManager = new BorderLayout();
        initLeftPanel();
        initRightPanel();
        initSouthPanel();
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(southPanel,BorderLayout.SOUTH);
    }

    private void initSouthPanel() {

        southPanel.setText("ceci est un test");
    }

    private void initRightPanel() {
        rightPanel.add(new PolygonPanel().affiche(3,0d, 10d));


    }

    private void initLeftPanel() {
        LayoutManager gridLayout = new GridLayout(3,1);
        JSpinner sideNbSpinner = new JSpinner();
        sideNbSpinner.setValue(3);
        settingSpinners.add(sideNbSpinner);

        leftPanel.add(sideNbSpinner, gridLayout);
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0,0,180,0.1);
        JSpinner angleSpinner = new JSpinner(spinnerNumberModel);

        settingSpinners.add(angleSpinner);
        leftPanel.add(angleSpinner);

        SpinnerNumberModel spinnerFactorModel = new SpinnerNumberModel(1,1,25,0.1);
        JSpinner multiplicator = new JSpinner(spinnerFactorModel);

        settingSpinners.add(multiplicator);
        leftPanel.add(multiplicator);

        leftPanel.setLayout(gridLayout);


        for ( JSpinner setting : settingSpinners ) {
            setting.addChangeListener(e -> {
                southPanel.removeAll();
                this.sideNb = (int) sideNbSpinner.getValue();
                this.angle = (double) angleSpinner.getValue();
                this.factor = (double) multiplicator.getValue();
                rightPanel.removeAll();
                PolygonPanel polygonPanel = new PolygonPanel();
                rightPanel.add(polygonPanel.affiche(sideNb, angle, factor));
                rightPanel.repaint();


                Polygon polygon = new Polygon();
                for (int i = 0; i < sideNb; i++) polygon.addPoint((int) (
                        100 + 10 * factor * Math.cos(angle+(i * 2 * Math.PI / sideNb))),(int) (
                        100 + 10 * factor * Math.sin(angle+((i * 2 * Math.PI / sideNb)))));

                int[] xCoordinates = polygon.xpoints;
                int[] yCoordonites = polygon.ypoints;

                this.coordinates = new StringBuilder();
                for (int i = 0; i < polygon.npoints; i++){
                    coordinates.append(xCoordinates[i] - 100)
                            .append(",")
                            .append(yCoordonites[i] - 100);
                }
                southPanel.setText(coordinates.toString());


            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setTitle("DrawPoly");
        frame.setSize(350, 250);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();

        contentPane.add(new PolygonCreatorPanel());
        frame.show();
    }
}