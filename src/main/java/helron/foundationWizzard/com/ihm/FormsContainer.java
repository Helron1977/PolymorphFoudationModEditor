package helron.foundationWizzard.com.ihm;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import javax.swing.*;
import java.awt.*;

/**
 * Define a Panel that contains all forms.
 */
public class FormsContainer extends JTabbedPane {

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Build and set  FormsContainer object that is a JtabbedPane.
     * Instanciate a form set as a tab in the TabbedPane.
     * @param class_ID a Tab name
     * @param structures Data source of the form to generate in this tab
     */
    public FormsContainer(String class_ID, ApiStructuresExtractor structures) {

        JPanel panel = new Form(class_ID, structures, this);
        this.add(class_ID, panel);

            setBounds(40, 20, 300, 300);
            setBackground(Color.white);
    }
}