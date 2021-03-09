package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import helron.foundationwizard.com.ui.customcomponents.irregularpolygons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Ntest {
    private static final JFrame frame = new JFrame();
    JPanel editorPanel;


    public static void showOnFrame(String frameName) throws IOException {
        frame.setTitle(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600,550));
        frame.setResizable(false);

        JPanel editorContainer = new EditorContainer();


        frame.addWindowListener(wa);
        frame.getContentPane().add(editorContainer);
        frame.pack();
    }

    public static void main(String[] args) throws IOException {
        showOnFrame("test");
    }

}

