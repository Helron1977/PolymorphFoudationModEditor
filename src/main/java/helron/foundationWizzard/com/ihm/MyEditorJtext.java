package helron.foundationWizzard.com.ihm;

import javax.swing.*;

public class MyEditorJtext extends JTextArea {
    public MyEditorJtext(String text) {
        super(text);
    }

    public MyEditorJtext(String text, int rows, int columns) {
        super(text, rows, columns);
    }
}
