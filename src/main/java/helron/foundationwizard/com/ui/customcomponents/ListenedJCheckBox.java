package helron.foundationwizard.com.ui.customcomponents;

import helron.foundationwizard.com.ui.FormCLass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenedJCheckBox extends JCheckBox implements FocusListener {
    private String label;
    private FormCLass activeFormCLass;

    public ListenedJCheckBox() {

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
