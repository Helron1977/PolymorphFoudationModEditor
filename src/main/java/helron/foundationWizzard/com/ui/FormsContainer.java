package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureClass;
import helron.foundationWizzard.com.datagenerator.DataStructureMap;
import helron.foundationWizzard.com.datagenerator.DataStructureType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Define a Panel that contains all forms.
 */
public class FormsContainer extends JTabbedPane {
    LinkedList<Form> formLinkedList;

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Build and set  FormsContainer object that is a JtabbedPane.
     * Instanciate a form set as a tab in the TabbedPane.
     * @param classID a Tab name
     * @param dataMap the DataStructureMap source of the form to generate in this tab
     */
    public FormsContainer(String classID, DataStructureMap dataMap) throws IOException {

        if(dataMap.getDataMap().get(classID).getClassType().equals(DataStructureType.CLASS)) {

            Form form = new Form(dataMap,classID, FormType.CLASS);

            this.add(form.id,form);
            setBounds(40, 20, 300, 300);
        }
    }
}