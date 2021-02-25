package helron.foundationWizzard.com.ui.customcomponents;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

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
                return file.getName().endsWith(".jsonc");
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
                String configPath="../config.properties";
                if(!new File(configPath).exists())
                {
                    try {
                        FileWriter fw = new FileWriter(configPath);
                        fw.write("Path="+ dialogue.getSelectedFile().toString().replace("\\","\\\\"));
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else
                {
                    Properties config = new Properties();
                    try {
                        config.load(new FileInputStream(configPath));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    config.setProperty("Path", dialogue.getSelectedFile().toString().replace("\\","\\\\"));

                }

            }
        };



        addMouseListener(ma);
    }


}
