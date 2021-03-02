package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIHelper {
    public static void showOnFrame(JComponent component, String frameName){
        JFrame frame = new JFrame(frameName);
        WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(windowAdapter);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}
