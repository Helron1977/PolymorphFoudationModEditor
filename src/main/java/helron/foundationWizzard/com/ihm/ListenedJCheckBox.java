package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedJCheckBox extends JCheckBox implements FocusListener {
    String label;

    public ListenedJCheckBox(String linkedlabel) {
        this.label = linkedlabel;
        addFocusListener(this);
    }


    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.white);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(new Color(0xAFF3C1));
        Form.inputs.put(label,isSelected()?"true":"false");
        LuaGenerator lg = new LuaGenerator(Form.inputs);
        System.out.println(lg.InitializeLuaTable("myMod"));
    }
}
