package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class ListenedJComboBox<E> extends JComboBox<String> implements FocusListener {
    String lbl;

    public ListenedJComboBox(String lbl) {
        this.lbl = lbl;

        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        Form.inputs.put(lbl, Objects.requireNonNull(getSelectedItem()).toString());
        setBackground(new Color(0xAFF3C1));
        LuaGenerator lg = new LuaGenerator(Form.inputs);
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
