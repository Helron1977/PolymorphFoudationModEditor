package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructureSet;

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
     * @param structures Data source of the form to generate in this tab
     */
    public FormsContainer(String classID, DataStructureSet dataMap) throws IOException {

/*        if(dataMap.isClass(classID)) {
            dataMap.getClassData(classID);
            Form form = new Form(dataMap.getClassData(classID), FormType.CLASS);

            this.add(form.getForm());
            setBounds(40, 20, 300, 300);
            System.out.println(indexOfTab(classID));
        }*/
    }
}