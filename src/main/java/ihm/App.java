package ihm;

import api.ApiStructuresExtractor;

import java.awt.*;

public class App {
    ApiStructuresExtractor structures;

    public App(ApiStructuresExtractor structures) {
        this.structures = structures;
        Frame test = new My_Frame("BUILDING WIZARD");
        My_Tabs my_tab = new My_Tabs("BUILDING", structures);
        test.add(my_tab);

    }
}
