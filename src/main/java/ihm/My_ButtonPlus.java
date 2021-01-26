package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;

public class My_ButtonPlus extends JButton {
    public My_ButtonPlus(ApiStructuresExtractor structures, String class_ID, My_Tabs tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                My_Pane tab= new My_Pane(class_ID, structures, tabs);
                tabs.add(class_ID,tab);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
