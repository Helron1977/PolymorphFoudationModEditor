package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import java.awt.*;

public class FormRow extends JPanel{
    private String labelText;
    private final JComponent field;
    private final int lineNumber;

    public FormRow (String labelText, JComponent field, int lineNumber) {
        this.labelText = labelText;
        this.field = field;
        this.lineNumber = lineNumber;

        GridBagLayout rowLayout = new GridBagLayout();

        JTextField label = new JTextField(labelText);
            label.setBackground(Color.white);
            label.setOpaque(true);
            label.setEditable(false);

        GridBagConstraints rowConstraints = new GridBagConstraints();
            rowConstraints.gridx = 0;
            rowConstraints.gridwidth = 1;
            rowConstraints.gridy= lineNumber;
            rowConstraints.fill= GridBagConstraints.BOTH;
            rowLayout.setConstraints(label, rowConstraints);
        this.add(label);
            rowConstraints.gridx = 1;
            rowConstraints.gridwidth = 1;
            rowConstraints.gridy = lineNumber;
            rowConstraints.fill = GridBagConstraints.BOTH;
            rowLayout.setConstraints(field, rowConstraints);
        this.add(field);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public JComponent getField() {
        return field;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
