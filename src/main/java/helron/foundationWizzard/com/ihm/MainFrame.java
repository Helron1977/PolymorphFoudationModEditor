package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Define a custom Frame that hold the UI
 * Dimension are set from the Forms dimension set values
 */
public class MainFrame extends JFrame{
    /**
     * Constructor af the main Frame
     * @param title a String set as title.
     */
    public MainFrame(String title) {
        super(title);

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

/*        Dimension frameDim = FormsContainer.dim;
            frameDim.setSize(FormsContainer.dim.width*3/4, FormsContainer.dim.height*3/4 );

        setSize(FormsContainer.dim);*/
        setVisible(true);
    }

}
