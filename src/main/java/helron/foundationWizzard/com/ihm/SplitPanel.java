package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import java.awt.*;

/**
 * This custom Panel allow to split a Panel in x columns.
 * the number of columns is the lenght of a JPanel array.
 * Be aware that panels will be add in the same order as they are store.
 */
public class SplitPanel extends JPanel {
    private JPanel[] panels;

    /**
     * Builder
     * @param panels an Array of JPanel.
     */
    public SplitPanel(JPanel[] panels ) {
        if (panels == null) {
            throw new IllegalArgumentException("Panels must be non null");
        }

        this.panels = panels ;

            setVisible(true);
            setOpaque(false);

        LayoutManager splitPanelLayout = new GridLayout(1,panels.length);
        setLayout(splitPanelLayout);

        for(JPanel panel : panels)
            add(panel);
    }

    public JPanel[] getPanels() {
        return panels;
    }

    public void setPanels(JPanel[] panels) {
        this.panels = panels;
    }
}
