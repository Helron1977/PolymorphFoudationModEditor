package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedTextField extends JTextField implements FocusListener {
    String lbl;

    public ListenedTextField(String lbl, String field) {
        super(field);
        this.lbl= lbl;

        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.white);

    }

    @Override
    public void focusLost(FocusEvent e) {
        Form.inputs.put(lbl, this.getText());
        setBackground(new Color(0xAFF3C1));
        LuaGenerator lg = new LuaGenerator(Form.inputs);
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
