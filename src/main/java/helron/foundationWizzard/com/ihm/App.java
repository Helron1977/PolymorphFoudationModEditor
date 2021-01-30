package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import java.awt.*;

public class App {
    ApiStructuresExtractor structures;

    public App(ApiStructuresExtractor structures) {
        this.structures = structures;
        Frame test = new MyFrame("FOUNDATION WIZARD");
        MyTabs myTab = new MyTabs("BUILDING", structures);
        test.add(myTab);
    }
}
