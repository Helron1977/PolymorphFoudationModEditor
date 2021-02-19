package helron.foundationWizzard.com.ui.customcomponents;

import helron.foundationWizzard.com.ui.Form;

import javax.swing.*;

public class ValidateButton extends JButton {
    public ValidateButton(Form activeTab) {
        super("Validate");
        this.addActionListener(e -> {
            try {

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
