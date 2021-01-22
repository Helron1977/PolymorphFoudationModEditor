package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame extends JFrame {
    public Frame() throws HeadlessException {
        super("Foundation Wizard");
        setLayout(null);


        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

        JPanel panel = new JPanel();
        setContentPane(panel);

    }
}
