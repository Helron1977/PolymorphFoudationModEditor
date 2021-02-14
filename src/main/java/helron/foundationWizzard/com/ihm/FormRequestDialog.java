package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import helron.foundationWizzard.com.ui.FormsContainer;

import javax.swing.*;
import java.awt.*;

public abstract class FormRequestDialog extends JDialog {
    protected String classID;
    protected ApiStructuresExtractor structures;
    protected FormsContainer tabsContainer;
    protected JTextField newIdField;


    /**
     * Dialog that set a Label and a field to set a value as Id or Name.
     * Add a validate Button that is Listened in subClass. the correct subClass has to be use depending of the kind of
     * form you are asking.
     * @param structures Dictionary of the API.
     * @param classID Asset request to known witch one call
     * @param tabsContainer tab Container to add a new one
     */
    public FormRequestDialog(ApiStructuresExtractor structures, String classID, FormsContainer tabsContainer){
        this.classID = classID;
        this.structures = structures;
        this.tabsContainer = tabsContainer;

        JLabel label = new JLabel(classID);
        newIdField = new JTextField();
            newIdField.setToolTipText("ID of the new Asset of "+ classID +" type." );
        JButton validateButton = new JButton("Validate");


        BorderLayout borderLayout = new BorderLayout();
            this.setLayout(borderLayout);

        this.add(validateButton,BorderLayout.SOUTH);
        this.addValidateButtonListener(validateButton);
        this.add(label,BorderLayout.WEST);
        this.add(newIdField,BorderLayout.CENTER);
            setLocationRelativeTo(newIdField);
            setSize(200,100);
            setVisible(true);

    }

     abstract void addValidateButtonListener(JButton Validate);
}
