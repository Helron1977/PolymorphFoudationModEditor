package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;

public class PlusButton extends JButton {
    public PlusButton(ApiStructuresExtractor structures, String class_ID, MyTabs tabs) {
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
