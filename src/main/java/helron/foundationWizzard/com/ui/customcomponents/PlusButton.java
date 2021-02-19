package helron.foundationWizzard.com.ui.customcomponents;

import javax.swing.*;

public class PlusButton extends JButton {
    String dataStructureID;
    public PlusButton(String dataStructureID) {
        super("+");
        this.dataStructureID = dataStructureID;
    }
}
