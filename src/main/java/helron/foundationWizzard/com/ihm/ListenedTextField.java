/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedTextField extends JTextField implements FocusListener {
    private String label;
    private Form activeForm;

    public ListenedTextField(String lbl, String fieldText, Form activeForm) {
        super(fieldText);
        this.label= lbl;
        this.activeForm = activeForm;
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.white);

    }

    @Override
    public void focusLost(FocusEvent e) {
        activeForm.getInputs().put(label, this.getText());
        setBackground(new Color(0xAFF3C1));
        LuaGenerator lg = new LuaGenerator(activeForm.getInputs());
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
*/
