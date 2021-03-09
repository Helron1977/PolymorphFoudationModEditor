package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import helron.foundationwizard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorContainer extends JPanel {
    private JPanel margLeft = new JPanel();
    private Canvas canvas = new Canvas();
    private ConsolePanel console;
    private ToolPanel toolsPanel;

    public EditorContainer() throws IOException {
        init();
    }

    private void init() throws IOException {
        setPreferredSize(new Dimension(600,500));
        setVisible(true);
        setOpaque(false);
        setLayout(new BorderLayout());

        margLeft = new JPanel();
        margLeft.setOpaque(false);
        margLeft.setPreferredSize(new Dimension(30,400));
        add(margLeft,BorderLayout.WEST);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(400,400));
        canvas.setBackground(new Color(106, 106, 107, 255));
        PolygonMouseCreator listener = new PolygonMouseCreator(canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);
        add(canvas, BorderLayout.CENTER);


        this.console = new ConsolePanel(this);

        this.toolsPanel = new ToolPanel(this, canvas);



        toolsPanel.getRemoveButton().addActionListener(e-> canvas.clearDrawables());
        toolsPanel.getCollapseButton().addActionListener(e-> {
            Area finalPoly = new Area();
            for(Shape shape : canvas.getDrawables()){
                finalPoly.add(new Area(shape));
            }
            canvas.clearDrawables();
            canvas.addDrawable(finalPoly);
        });

        toolsPanel.getValidateButton().addActionListener(e-> {

            toolsPanel.getCollapseButton().doClick();


            List<String> points = new ArrayList<>();

            for (Point2D.Float pointFloat : canvas.getClickedPoints()) {
                points.add("{ "+String.valueOf(pointFloat.x) + " ; " + String.valueOf(pointFloat.y) +" }");

            }
            console.setConsoleText(points.toString().replace("[","{")
            .replace("]","}")
            .replace(", ", ", \n"));

        });
    }

    public ConsolePanel getConsole() {
        return console;
    }
}
