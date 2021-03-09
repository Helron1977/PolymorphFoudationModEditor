package helron.foundationwizard.com.ui.customcomponents.irregularpolygons;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class ConsolePanel extends JPanel{
    JPanel editorContainer;
    private final JTextArea console = new JTextArea();

    public ConsolePanel(JPanel editorContainer) {
        this.editorContainer = editorContainer;

        initConsole();
    }

    private void initConsole() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setVisible(true);

        console.setPreferredSize(new Dimension(400,90));
        console.setAutoscrolls(true);
        console.setForeground(Color.WHITE);
        console.setOpaque(false);
        console.setFont(new Font(" TimesRoman ",Font.BOLD,16));
        console.setText("""
        Left click add a point     Right click draw polygon 
                you can collapse several polygon
                but you ll have to reselect summit yourself and press the validate button""");
        console.setVisible(true);



        add(console, BorderLayout.CENTER);
        editorContainer.add(this, BorderLayout.SOUTH);
    }

    public void setConsoleText(String text) {
        console.setText(text);
    }

    public String getConsoleText(){
        return console.getText();
    }

    public Document getDocument(){
        return  console.getDocument();
    }
}
