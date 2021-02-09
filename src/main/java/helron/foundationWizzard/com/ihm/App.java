package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class App {
    ApiStructuresExtractor structures;

    public App(ApiStructuresExtractor structures) throws IOException {
        this.structures = structures;
        Frame frame = new MainFrame("FOUNDATION WIZARD");
        Panel panel = new Panel();
        FormsContainer myTab = new FormsContainer("BUILDING", structures);
        panel.add(myTab,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame("test");

        JImagePanel jip = new JImagePanel("src/main/resources/splash.png");
        jip.setStretch(true);
        f.add(jip);
        f.setVisible(true);
    }
}
