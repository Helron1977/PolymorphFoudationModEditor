/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class ListenedJComboBox<E> extends JComboBox<String> implements FocusListener {
    private final String label;
    private final Form activeForm;

    public ListenedJComboBox(String lbl, Form activeForm) {
        this.label = lbl;
        this.activeForm = activeForm;

        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        activeForm.getInputs().put(label, Objects.requireNonNull(getSelectedItem()).toString());
        setBackground(new Color(0xAFF3C1));
        LuaGenerator lg = new LuaGenerator(activeForm.getInputs());
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
*/
