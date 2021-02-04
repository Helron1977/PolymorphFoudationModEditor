package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import java.awt.*;
import java.io.IOException;

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
}
