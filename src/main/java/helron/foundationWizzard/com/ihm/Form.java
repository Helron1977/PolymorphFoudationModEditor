/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.data.DataSet;
import helron.foundationWizzard.com.data.Param;
import helron.foundationWizzard.com.ui.FormsContainer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

*/
/**
 * Build and set a  Form from ApiStructuresExtractor data source.
 * Separate params and sort fields to generate the correct JComponent Type.
 * Each rows and each param/fieldType are using the same Method to be build.
 * A plus button allow the user to build a new form in a new tab of the FormsContainer.
 *//*

public class Form extends JPanel {
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private FormsContainer formsContainer;
    public static LinkedHashMap<String,String> inputs = new LinkedHashMap<>();
    private final String classID;
    private String assetID;


    public Form(String classID, DataSet dataMap, FormsContainer formsContainer) {
        this.classID = classID;
        this.formsContainer = formsContainer;

            setLayout(gb);
            setBackground(Color.white);
            setSize(dim);
            setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

            if (dataMap.isClass(classID))
                buildEachRows(dataMap.getClassData(classID).getParamLinkedList());
    }

*/
/*    public Form(String class_ID, ApiStructuresExtractor structures, FormsContainer formsContainer, String assetID) {
        this.classID = class_ID;
        this.structures = structures;
        inputs = new LinkedHashMap<>();
        this.formsContainer = formsContainer;
        this.assetID = assetID;

        setLayout(gb);
        setBackground(Color.white);
        setSize(dim);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params);
    }*//*


*/
/*    public Form(String classID, ApiStructuresExtractor apiIndex) {
        this.classID = classID;

        setLayout(gb);
        setBackground(Color.white);
        setSize(dim);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = apiIndex.extractClass(classID);
        buildEachRows(params);

    }*//*


    */
/**
     * Build and set a Jbutton that validate the form and generate the lua code.
     * @param lineNumber the line number of the button
     *//*

    private void addValidateButton(int lineNumber) {
        ValidateButton bt = new ValidateButton(this);
            gbc.gridx = 7;
            gbc.gridwidth = 0;
            gbc.gridy = lineNumber+1;
            gbc.fill = GridBagConstraints.REMAINDER;
            gb.setConstraints(bt, gbc);
        this.add(bt);
    }

    */
/**
     * Build each Rows following the number of the params inside a Structure description.
     * @param params LinkedHashMap description of a Structure, result of ApiStructuresExtractor.extractClass()
    */

/*    private void buildEachRows(LinkedList<Param> params) {
        int cptRow = 0;
        int finalCptRow = cptRow;
        params.forEach((key, value) -> buildFormRow(key,value, finalCptRow, key.getDefaultValue()));


            String fullParamDescription = params.get(param);
            String paramType = structures.extractParamType(fullParamDescription);
            String defaultValue = structures.extractParamDefaultValue(fullParamDescription);

            buildFormRow(param, paramType, cptRow, defaultValue);
            cptRow++;

        addValidateButton(cptRow);
    }*/

    /**
     * Build a couple of Jlabel, Jcomponent from Strings, a line number and a String defaultValue
     * The Jcomponent to create can be deducted of the string, field, that contains identifiable subString.
     * @param label use to set the Jlabel Text
     * @param field use to identify the JComponent
     * @param lineNumber int, line number in the form
     * @param defaultValue default value extract from ApiStructuresExtractor.extractParamDefaultValue
     */

/*    private void buildFormRow(String label, String field, int lineNumber, String defaultValue) {

        JTextField lbl = new JTextField(label);
            lbl.setBackground(Color.white);
            lbl.setOpaque(true);
        if (lineNumber % 2 == 1) {
            lbl.setBackground(Color.white);
        } else {
            lbl.setBackground(new Color(0x22471D));
            lbl.setForeground(Color.white);
        }
        lbl.setEditable(false);
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridy= lineNumber;
            gbc.fill= GridBagConstraints.BOTH;
            gb.setConstraints(lbl, gbc);
        this.add(lbl);*/

/*        buildField(field, lineNumber, defaultValue, label);
    }*/


/**
     * Build the correct JComponent needed to represent the field.
     * Invoke identifyType()
     * @param field string, the field value
     * @param lineNumber int, the line number
     * @param defaultValue string the default value to set the component
     * @param label the label linked to this field
     *//*

    private void buildField(String field, int lineNumber, String defaultValue, String label) {

        if(label.equals("DataType")) {
            JTextField jtf = new JTextField(classID);
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.gridy = lineNumber;
            gb.setConstraints(jtf, gbc);
            jtf.setEditable(false);
            this.add(jtf);

            inputs.put(label, classID);
        }else if(label.equals("Id") && assetID != null) {
            JTextField jtf = new JTextField(assetID);
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.gridy = lineNumber;
            gb.setConstraints(jtf, gbc);
            jtf.setEditable(false);
            this.add(jtf);

            inputs.put(label, defaultValue);

        } else{
            FieldType fieldType = identifyType(field);


            //TODO add a case Polygon
            switch (Objects.requireNonNull(fieldType)) {
                case STRING:
                    ListenedTextField jt = new ListenedTextField(label, field,this);
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gb.setConstraints(jt, gbc);
                    this.add(jt);

                    inputs.put(label, defaultValue);
                    break;
                case ASSET_ENUM:
                    ListenedJComboBox<String> jbc = new ListenedJComboBox<>(label, this);
                    if (DataSet.isAsset(field)) {
                        for (String value : structures.assetToList(field)) {
                            jbc.addItem(value);
                        }

                        if (defaultValue != null) {
                            jbc.addItem(defaultValue);
                            jbc.setSelectedItem(defaultValue);
                        }
                        gbc.gridx = 1;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.BOTH;
                        gb.setConstraints(jbc, gbc);
                        this.add(jbc);

                        PlusButton bt = new PlusButton(jbc,structures, field, formsContainer);
                        gbc.gridx = 2;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.REMAINDER;
                        gb.setConstraints(bt, gbc);
                        this.add(bt);

                        inputs.put(label, defaultValue);
                        break;
                    } else if (structures.isEnum(field)) {
                        for (String value : structures.enumToList(field)) {
                            jbc.addItem(value);
                        }
                        if (defaultValue != null) {
                            jbc.setSelectedItem(defaultValue);
                        }
                        gbc.gridx = 1;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.BOTH;
                        gb.setConstraints(jbc, gbc);
                        this.add(jbc);

                        inputs.put(label, defaultValue);
                        break;
                    }
                case LIST:
                    field = field.replaceAll("list<", "")
                            .replaceAll(">", "");

                    DefaultListModel<String> listData = new DefaultListModel<>();
                    ListenedJList jl = new ListenedJList(listData,label,this);

                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jl, gbc);
                    this.add(jl);


                    PlusButton bt = new PlusButton(listData, jl, structures, field, formsContainer);
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.REMAINDER;
                    gb.setConstraints(bt, gbc);
                    this.add(bt);

                    inputs.put(label, defaultValue);
                    break;

                case BOOLEAN:
                    ListenedJCheckBox jcb = new ListenedJCheckBox(label, this);
                    if (defaultValue != null) {
                        if (defaultValue.equals("true"))
                            jcb.setSelected(true);
                        else if (defaultValue.equals("false"))
                            jcb.setSelected(false);
                    }
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jcb, gbc);
                    this.add(jcb);

                    inputs.put(label, defaultValue);
                    break;
                case INTEGER:
                    ListenedJSpinner js = new ListenedJSpinner(label, this);
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(js, gbc);
                    this.add(js);

                    inputs.put(label, defaultValue);
                    break;
                case FLOAT:
                    float value = 0f;
                    float min = 0f;
                    float max = 100f;
                    float step = 0.01f;

                    SpinnerNumberModel myFloatSpinner = new SpinnerNumberModel(value, min, max, step);

                    JSpinner jfs = new JSpinner(myFloatSpinner);
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jfs, gbc);
                    this.add(jfs);

                    inputs.put(label, defaultValue);
                    break;
            }
        }
    }

}
*/
