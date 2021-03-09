package helron.foundationwizard.com.ui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedTextField extends JTextField implements FocusListener {
    private String label;

    public ListenedTextField(String value) {
        super(value);

        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.white);

    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(new Color(0xAFF3C1));
    }
}
