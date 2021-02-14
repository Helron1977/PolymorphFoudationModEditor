/*
package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ListenedJSpinner extends JSpinner implements ChangeListener {
    private final String label;
    private Form activeForm;
    public ListenedJSpinner(String linkedLabel, Form activeForm) {
        this.label = linkedLabel;
        this.activeForm = activeForm;
        addChangeListener(this);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        activeForm.getInputs().put(label,getValue().toString());
        JComponent editor = getEditor();
        int n = editor.getComponentCount();
        for (int i=0; i<n; i++)
        {
            Component c = editor.getComponent(i);
            if (c instanceof JTextField)
            {
                c.setBackground(new Color(0xAFF3C1));
            }
        }

    }
}
*/
