package helron.foundationWizzard.com.ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JtextFileChooser extends JTextField {
    public JtextFileChooser(String text) {
        setText(text);
        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Color.white);
        setFont(new Font("Serif", Font.BOLD, 20));
        setOpaque(false);
        setEditable(false);
        setBorder(new EmptyBorder(0,0,0,0));

        JFileChooser dialogue = new JFileChooser(new File("."));
        dialogue.setApproveButtonText("Select");
        dialogue.setDialogTitle("Select the Snippet File");
        dialogue.setApproveButtonToolTipText("Choose this file as new Foundation API description");
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.getName().endsWith(".jsonc")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Jsonc file delivered under Polymorh folder";
            }
        };
        dialogue.setFileFilter(fileFilter);

        dialogue.setDragEnabled(true);

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dialogue.showOpenDialog(null);
            }
        };

//        dialogue.getSelectedFile();

        addMouseListener(ma);
    }




}
