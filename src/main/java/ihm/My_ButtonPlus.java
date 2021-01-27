package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;

public class My_ButtonPlus extends JButton {
    public My_ButtonPlus(ApiStructuresExtractor structures, String class_ID, MyTabs tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                MyPane tab= new MyPane(class_ID, structures, tabs);
                tabs.add(class_ID,tab);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
