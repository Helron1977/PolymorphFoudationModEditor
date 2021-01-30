package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class MyFrame extends JFrame{

    public MyFrame(String title) {
        super(title);

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

        Dimension frameDim = MyTabs.dim;
            frameDim.setSize(MyTabs.dim.width,MyTabs.dim.height );

        setSize(MyTabs.dim);
        setVisible(true);
    }

}
