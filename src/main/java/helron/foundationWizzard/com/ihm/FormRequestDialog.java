package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FormRequestDialog extends JDialog {

    /**
     * Jdialog that ask the id of the new asset. Set this value in the previous JComboBox and select it.
     * Focus and UnFocus the field
     * @param jbc the JComboBOx to add the input Value
     * @param structures Dictionnary of the API
     * @param class_ID Asset request to known wichone call
     * @param tabs tab Container to add a new one
     */
    public FormRequestDialog(ListenedJComboBox<String> jbc, ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {

        JLabel lbl = new JLabel(class_ID);
        JTextField jtf = new JTextField();
        jtf.setToolTipText("ID of the new Asset of "+ class_ID +" type." );
        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        JButton jb = new JButton("Validate");
        jb.addActionListener(e -> {
            String assetID= jtf.getText();

            //set value in previous JComboBox
            jbc.addItem(assetID);
            jbc.setSelectedItem(assetID);

            //new tab for the new Form
            //TODO Set the Id value in the new Form
            Form tab= new Form(class_ID, structures, tabs, assetID );
            tab.setOpaque(false);
            tabs.add(class_ID,tab);

            //Close the dialog and free resources
            setVisible(false);
            dispose();
        });
        add(jb,BorderLayout.SOUTH);

        add(lbl,BorderLayout.WEST);
        add(jtf,BorderLayout.CENTER);

        setLocationRelativeTo(jbc);
        setSize(200,100);
        setVisible(true);
    }
    public FormRequestDialog(DefaultListModel<String> listdata, JList<String> jList, ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {

        JLabel lbl = new JLabel(class_ID);
        JTextField jtf = new JTextField();
        jtf.setToolTipText("ID of the new Asset of "+ class_ID +" type." );
        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        JButton jb = new JButton("Validate");
        jb.addActionListener(e -> {
            String assetID= jtf.getText();
            //set value in previous JComboBox
            listdata.addElement(assetID);
            jList.setModel(listdata);


            //new tab for the new Form
            //TODO Set the Id value in the new Form
            Form tab= new Form(class_ID, structures, tabs, assetID );
            tab.setOpaque(false);
            tabs.add(class_ID,tab);

            //Close the dialog and free resources
            setVisible(false);
            dispose();
        });
        add(jb,BorderLayout.SOUTH);

        add(lbl,BorderLayout.WEST);
        add(jtf,BorderLayout.CENTER);

        setLocationRelativeTo(jtf);
        setSize(200,100);
        setVisible(true);
    }
}
