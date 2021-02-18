/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedJCheckBox extends JCheckBox implements FocusListener {
    private String label;
    private Form activeForm;

    public ListenedJCheckBox(String linkedlabel, Form activeForm) {
        this.label = linkedlabel;
        this.activeForm = activeForm;
        addFocusListener(this);
    }


    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.white);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(new Color(0xAFF3C1));
        activeForm.getInputs().put(label,isSelected()?"true":"false");
        LuaGenerator lg = new LuaGenerator(activeForm.getInputs());
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
*/
