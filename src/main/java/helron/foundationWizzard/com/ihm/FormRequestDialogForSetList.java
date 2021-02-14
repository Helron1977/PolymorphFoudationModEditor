/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import helron.foundationWizzard.com.ui.FormsContainer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FormRequestDialogForSetList extends FormRequestDialog{
    private final DefaultListModel<String> dataList;
    private final JList<String> jList;

    */
/**
     * Jdialog that set a Label and a field to set a value as Id or Name.
     * Add a validate Bouton that is Listened here.
     * @param structures    Dictionnary of the API.
     * @param classID       Asset request to known witch one call
     * @param tabsContainer tab Container to add a new one
     *//*

    public FormRequestDialogForSetList(DefaultListModel<String> dataList,JList<String> jList,ApiStructuresExtractor structures, String classID, FormsContainer tabsContainer) {
        super(structures, classID, tabsContainer);
        this.dataList = dataList;
        this.jList = jList;
    }


    */
/**
     * When the user ask a new List type form, the previous List containing existing asset has to be
     * update with the input value. this value will set the name of a new lua LocalVar Script.
     * @param validate This is the JButton that need to be listened.
     *//*

    @Override
    void addValidateButtonListener(JButton validate) {
        validate.addActionListener(e -> {
            String newIdFieldText = newIdField.getText();
            //set value in previous JComboBox
            dataList.addElement(newIdFieldText);


            //new FormSetList( dataList, structures,classID, tabsContainer, newIdFieldText);
            JPanel ListContainer = new JPanel();

            JPanel setListContainer = new JPanel();
                JList<String> SetList = new JList<>();
                SetList.setModel(dataList);
            LayoutManager setLisContainerlayout = new BorderLayout();
                setListContainer.setLayout(setLisContainerlayout);
                setListContainer.add(SetList,BorderLayout.WEST);
                setListContainer.setOpaque(true);

            //new tab for the new Form
            Form form = new Form(classID, structures, tabsContainer, newIdFieldText);
            form.setOpaque(true);

            //build an Array of JPanel
            JPanel[] newTabContent = {setListContainer,form};

            //new split panel
            SplitPanel setListFormContainer = new SplitPanel(newTabContent);
            ListContainer.add(setListFormContainer);

            tabsContainer.add(classID, ListContainer);

            try {
                tabsContainer.setTabComponentAt(tabsContainer.indexOfTab(classID),tabsContainer.getTitlePanel( ListContainer, classID));
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
