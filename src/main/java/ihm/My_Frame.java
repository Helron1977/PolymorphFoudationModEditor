package ihm;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class My_Frame extends JFrame{

    public My_Frame(String title) {
        super(title);

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        setVisible(true);
        setSize(My_Tabs.dim);
    }

}
