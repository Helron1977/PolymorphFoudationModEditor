package helron.foundationWizzard.com.ihm;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private final BufferedImage image;
    public ImagePanel() throws IOException {
        image = ImageIO.read(new File("src/main/resources/img.png"));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
