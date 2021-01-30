package helron.foundationWizzard.com.ihm;

import javax.swing.*;

public class ValidateButton extends JButton {
    public ValidateButton() {
        super("Validate");
        this.addActionListener(e -> {
            try {
                System.out.println(this);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
