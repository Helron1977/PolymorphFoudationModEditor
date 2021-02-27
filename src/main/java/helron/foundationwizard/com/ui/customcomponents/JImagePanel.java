package helron.foundationwizard.com.ui.customcomponents;


import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Load and display an image in a panel
 * @author fobec 2010
 */
public class JImagePanel extends JPanel {

    private Image image = null;
    private Boolean stretch = true;

    /**
     * Builder
     * @param image image to display
     */
    public JImagePanel(Image image) {
        this.image = image;
    }

    /**
     * Builder
     * @param file file Name
     */
    public JImagePanel(String file) {
        this.image = getToolkit().getImage(file);
    }

    /**
     * Image position in the panel
     * @param stretch true: Strech the image / false: center the image
     */
    public void setStretch(Boolean stretch) {
        this.stretch = stretch;
    }

    /**
     * Overdrive the draw of the panel
     * @param g canvas
     */
    protected void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (this.stretch) {
            width = this.getWidth();
            height = this.getHeight();
        } else {
            width = this.image.getWidth(this);
            height = this.image.getHeight(this);
            x=((this.getWidth()-width)/2);
            y=((this.getHeight()-height)/2);
        }
        g.drawImage(this.image, x, y, width, height, this);
    }

    /**
     * test : jPanelImage dans un JFrame
     * @param args
     */
    public static void main(String[] args) {
        JImagePanel imagePanel = new JImagePanel("/fiefdomLogo.png");
        //Strech the image
        imagePanel.setStretch(false);
        //center the image
        // imagePanel.setStretch(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(imagePanel);
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}