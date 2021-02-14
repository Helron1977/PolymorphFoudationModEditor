/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;

public class MutipleFormTabbed{
    String classID;

    public MutipleFormTabbed(String classID) {
        this.classID = classID;
        FoundationAPI foundationAPI = new FoundationAPI();
        ApiStructuresExtractor apiStructures = foundationAPI.getStructures();


        JTabbedPane panneauAOnglet = new JTabbedPane();
        GridBagLayout gb = new GridBagLayout();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            panneauAOnglet.setLayout(gb);
            panneauAOnglet.setBackground(Color.white);
            panneauAOnglet.setSize(dim);

        Form onglet = new Form(classID,apiStructures);
            panneauAOnglet.setLocation(dim.width/2 - onglet.getWidth()/2, dim.height/2 - onglet.getHeight()/2);

    }

    public static void main(String[] args) {
        new MutipleFormTabbed("BUILDING");
    }
}
*/
