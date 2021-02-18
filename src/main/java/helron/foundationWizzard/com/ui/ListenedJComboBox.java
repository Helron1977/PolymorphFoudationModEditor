package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class ListenedJComboBox<E> extends JComboBox<String> implements FocusListener {


    public ListenedJComboBox() {

        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(new Color(0xAFF3C1));

    }
}
