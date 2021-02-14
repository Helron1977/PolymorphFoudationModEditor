package helron.foundationWizzard.com.ui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class JTextFieldLink extends JTextField {
    public JTextFieldLink(String text, String Link) {
        super(text);
        setForeground(Color.white);
        setFont(new Font("Serif", Font.BOLD, 20));
        setOpaque(false);
        setEditable(false);
        setBorder(new EmptyBorder(0,0,0,0));
        setHorizontalAlignment(JTextField.HORIZONTAL);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try { Desktop.getDesktop().browse(new URI(Link));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }

            }
        });
    }
}
