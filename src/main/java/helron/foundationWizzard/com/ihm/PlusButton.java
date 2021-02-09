package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;

public class PlusButton extends JButton {
    public PlusButton(ListenedJComboBox<String> jbc, ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                FormRequestDialog rq = new FormRequestDialog(jbc, structures, class_ID, tabs);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
    public PlusButton(ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                Form tab= new Form(class_ID, structures, tabs);
                tabs.add(class_ID,tab);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
