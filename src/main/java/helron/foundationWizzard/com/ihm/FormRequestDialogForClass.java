/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import helron.foundationWizzard.com.ui.FormsContainer;

import javax.swing.*;
import java.io.IOException;

public class FormRequestDialogForClass extends FormRequestDialog{
    ListenedJComboBox<String> existingAssetList;
    */
/**
     * Dialog that set a Label and a field to set a value as Id or Name.
     * Add a validate Button that is Listened here.
     * @param structures    Dictionary of the API
     * @param classID       Asset request to known wichone call
     * @param tabsContainer tab Container to add a new one
     *//*

    public FormRequestDialogForClass(ListenedJComboBox<String> existingAssetList, ApiStructuresExtractor structures, String classID, FormsContainer tabsContainer) {
        super(structures, classID, tabsContainer);
        this.existingAssetList = existingAssetList;
    }

    */
/**
     * When the user ask a new Class type form, the previous ComboBox containing existing asset has to be update
     * with the input value. this value will set the ID field of a new Form.
     * @param validate This is the JButton that need to be listened.
     *//*

    @Override
    void addValidateButtonListener(JButton validate) {
        validate.addActionListener(e -> {
            String newAssetID = newIdField.getText();

            //set value in previous JComboBox
            existingAssetList.addItem(newAssetID);
            existingAssetList.setSelectedItem(newAssetID);

            //new tab for the new Form
            //TODO Set the Id value in the new Form
            Form form = new Form(super.classID, super.structures, super.tabsContainer, newAssetID);
                form.setOpaque(false);
            tabsContainer.add(classID, form);

            try {
                tabsContainer.setTabComponentAt(tabsContainer.indexOfTab(classID),tabsContainer.getTitlePanel( form, classID));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            //Close the dialog and free resources
            setVisible(false);
            dispose();
        });
    }
}
*/
