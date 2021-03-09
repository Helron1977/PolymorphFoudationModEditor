package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class ToolPanel extends JPanel{
    private JPanel editorContainer;
    private JButton removeButton;
    private JButton collapseButton;
    private JButton validateButton;
    Canvas canvas;



    public ToolPanel(JPanel editorContainer, Canvas canvas) {
        this.editorContainer = editorContainer;
        this.canvas= canvas;
        init();
    }

    private void init() {
        GridBagLayout layoutManager = new GridBagLayout();
        setPreferredSize(new Dimension(170,500));
        setVisible(true);
        setOpaque(false);

        removeButton = new JButton("Remove");

        collapseButton = new JButton("Collapse");

        validateButton = new JButton("Validate");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;


        layoutManager.setConstraints(removeButton, gbc);
        layoutManager.setConstraints(collapseButton, gbc);
        layoutManager.setConstraints(validateButton, gbc);

        add(removeButton);
        add(collapseButton);
        add(validateButton);



        editorContainer.add(this, BorderLayout.EAST);
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getCollapseButton() {
        return collapseButton;
    }

    public JButton getValidateButton() {
        return validateButton;
    }
}
