package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;

public class MyTabs extends JTabbedPane {

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public MyTabs(String class_ID, ApiStructuresExtractor structures) {

        JPanel panel = new MyPane(class_ID, structures, this);
        this.add(class_ID, panel);

        setBounds(40, 20, 300, 300);
    }
}