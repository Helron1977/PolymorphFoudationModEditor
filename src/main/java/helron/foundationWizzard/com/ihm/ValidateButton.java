/*package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;

public class ValidateButton extends JButton {
    public ValidateButton(Form activeTab) {
        super("Validate");
        this.addActionListener(e -> {
            try {
                LuaGenerator lg = new LuaGenerator(activeTab.getInputs());
                new LuaScriptFrame(lg);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

}*/
