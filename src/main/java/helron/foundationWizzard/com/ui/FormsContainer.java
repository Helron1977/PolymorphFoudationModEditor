package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureMap;
import helron.foundationWizzard.com.datagenerator.DataStructureType;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Define a Panel that contains all forms.
 */
public class FormsContainer extends JTabbedPane implements FormSelector{
    LinkedList<Form> formLinkedList;
    DataStructureMap dataStructureMap;

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Build and set  FormsContainer object that is a JtabbedPane.
     * Instanciate a form set as a tab in the TabbedPane.
     * @param classID a Tab name
     * @param dataStructureMap the DataStructureMap source of the form to generate in this tab
     */
    public FormsContainer(String classID, DataStructureMap dataStructureMap, FormType formTypeRequest) throws IOException {
        this.dataStructureMap = dataStructureMap;

        DataStructure dataStructure = dataStructureMap.getDataMap().get(classID);

        init(classID, dataStructure);
    }

    private void init(String classID, DataStructure dataStructure) {


                Form form = new Form(dataStructure, classID);

                this.add(form.id, form);
                setBounds(40, 20, 300, 300);
                form.getValidateButton().addActionListener(e -> System.out.println("test"));
                for (PlusButton plusButton : form.getAddButtons()) {
                    plusButton.addActionListener(e->addPanel(plusButton));
                }
    }

    private void addPanel( PlusButton plusButton){

        String wantedStructureId= DataStructureType.CLASS.getPrefix()+plusButton.getStructureIdRequest();
        DataStructure wantedDataStructure = dataStructureMap.getDataMap().get(wantedStructureId);

        init(wantedStructureId,wantedDataStructure);
    }
}