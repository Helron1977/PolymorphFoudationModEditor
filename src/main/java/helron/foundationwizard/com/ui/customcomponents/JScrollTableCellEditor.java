package helron.foundationwizard.com.ui.customcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * This Class allows to display a Spinner in a cellTable by editing the cellEditor
 */
public class JScrollTableCellEditor extends DefaultCellEditor {

        JSpinner jSpinner;
        JSpinner.DefaultEditor defaultEditor;
        JTextField text;

        public JScrollTableCellEditor() {
            super(new JTextField());
            jSpinner = new JSpinner();
            defaultEditor = ((JSpinner.DefaultEditor) jSpinner.getEditor());
            text = defaultEditor.getTextField();
        }

        // Prépare le composant spinner et retourne-le
        public Component getTableCellEditorComponent(JTable table, Object
                value, boolean isSelected, int row, int column)
        {
                SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, -100, 100, 0.01);
                jSpinner.setModel(spinnerNumberModel);
                return jSpinner;
        }

    /**
     * Getter
     * @return the actual value of the cellSpinner
     */
    public Object getCellEditorValue() {
            return jSpinner.getValue();
        }
}
