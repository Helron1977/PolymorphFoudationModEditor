package helron.foundationWizzard.com.ui.customcomponents;

import helron.foundationWizzard.com.ui.FormCLass;

import javax.swing.*;

public class ValidateButton extends JButton {
    public ValidateButton(FormCLass activeTab) {
        super("Validate");
        this.addActionListener(e -> {
            try {

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
