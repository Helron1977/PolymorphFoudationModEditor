package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Vector;

/**
 * Build and set a  Form from ApiStructuresExtractor data source.
 * Separate params and sort fields to generate the correct JComponent Type.
 * Each rows and each param/fieldType are using the same Method to be build.
 * A plus button allow the user to build a new form in a new tab of the FormsContainer.
 */
public class Form extends JPanel {
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final FormsContainer formsContainer;
    private final ApiStructuresExtractor structures;
    public static final LinkedHashMap<String,String> inputs = new LinkedHashMap<>();
    private final String class_ID;
    private String assetID;

    /**
     * Build a Form
     * @param class_ID String, Name of the Asset to extract for witch a Form has to be build
     * @param structures ApiStructuresExtractor, the Data source.
     * @param formsContainer the tabbedPanel.
     */
    public Form(String class_ID, ApiStructuresExtractor structures, FormsContainer formsContainer) {
        this.class_ID = class_ID;
        this.structures = structures;
        this.formsContainer = formsContainer;

            setLayout(gb);
            setBackground(Color.white);
            setSize(dim);
            setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params);

    }

    public Form(String class_ID, ApiStructuresExtractor structures, FormsContainer formsContainer, String assetID) {
        this.class_ID = class_ID;
        this.structures = structures;
        this.formsContainer = formsContainer;
        this.assetID = assetID;

        setLayout(gb);
        setBackground(Color.white);
        setSize(dim);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params);
    }

    /**
     * Build and set a Jbutton that validate the form and generate the lua code.
     * @param lineNumber the line number of the button
     */
    private void addValidateButton(int lineNumber) {
        ValidateButton bt = new ValidateButton();
            gbc.gridx = 7;
            gbc.gridwidth = 0;
            gbc.gridy = lineNumber+1;
            gbc.fill = GridBagConstraints.REMAINDER;
            gb.setConstraints(bt, gbc);
        this.add(bt);
    }

    /**
     * Build each Rows following the number of the params inside a Structure description.
     * @param params LinkedHashMap description of a Structure, result of ApiStructuresExtractor.extractClass()
     */
    private void buildEachRows(LinkedHashMap<String, String> params) {
        int cptRow = 0;
        for( String param : params.keySet()){
            String fullParamDescription = params.get(param);
            String paramType = structures.extractParamType(fullParamDescription);
            String defaultValue = structures.extractParamDefaultValue(fullParamDescription);

            buildFormRow(param, paramType, cptRow, defaultValue);
            cptRow++;
        }
        addValidateButton(cptRow);
    }

    /**
     * Build a couple of Jlabel, Jcomponent from Strings, a line number and a String defaultValue
     * The Jcomponent to create can be deducted of the string, field, that contains identifiable subString.
     * @param label use to set the Jlabel Text
     * @param field use to identify the JComponent
     * @param lineNumber int, line number in the form
     * @param defaultValue default value extract from ApiStructuresExtractor.extractParamDefaultValue
     */
    private void buildFormRow(String label, String field, int lineNumber, String defaultValue) {

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
        this.add(lbl);

        buildField(field, lineNumber, defaultValue, label);
    }

    /**
     * Build the correct JComponent needed to represent the field.
     * Invoke identifyType()
     * @param field string, the field value
     * @param lineNumber int, the line number
     * @param defaultValue string the default value to set the component
     * @param label the label linked to this field
     */
    private void buildField(String field, int lineNumber, String defaultValue, String label) {

        if(label.equals("DataType")) {
            JTextField jtf = new JTextField(class_ID);
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.gridy = lineNumber;
            gb.setConstraints(jtf, gbc);
            jtf.setEditable(false);
            this.add(jtf);

            inputs.put(label, class_ID);
        }else if(label.equals("Id") && assetID != null) {
            JTextField jtf = new JTextField(assetID);
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.gridy = lineNumber;
            gb.setConstraints(jtf, gbc);
            jtf.setEditable(false);
            this.add(jtf);

            inputs.put(label, assetID);

        } else{
            FieldType fieldType = identifyType(field);


            //TODO add a case Polygon
            switch (Objects.requireNonNull(fieldType)) {
                case STRING:
                    ListenedTextField jt = new ListenedTextField(label, field);
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gb.setConstraints(jt, gbc);
                    this.add(jt);
                    break;
                case ASSET_ENUM:
                    ListenedJComboBox<String> jbc = new ListenedJComboBox<>(label);
                    if (structures.isAsset(field)) {
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

                        break;
                    }
                case LIST:
                    field = field.replaceAll("list<", "")
                            .replaceAll(">", "");


                    //ListenedTextField jtf = new ListenedTextField(label, field);
                    //Vector<String> listData=new Vector<>();
                    DefaultListModel<String> listData = new DefaultListModel<>();



                    ListenedJList jl = new ListenedJList(listData,label);

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

                    break;

                case BOOLEAN:
                    ListenedJCheckBox jcb = new ListenedJCheckBox(label);
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

                    break;
                case INTEGER:
                    ListenedJSpinner js = new ListenedJSpinner(label);
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(js, gbc);
                    this.add(js);

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

                    break;
            }
        }
    }

    /**
     * identify the param Type looking for substring
     * @param field String, the field to scan
     * @return int, an arbitrary value
     */
    private FieldType identifyType(String field) {

        //TODO add a sort by Polygon type
        if(field.contains("string")){
            return FieldType.STRING;
        }
        else if(field.equals(field.toUpperCase())){
            return FieldType.ASSET_ENUM;
        }
        else if(field.contains("list<")){
            return FieldType.LIST;
        }
        else if(field.contains("boolean")) {
            return FieldType.BOOLEAN;
        }
        else if(field.contains("integer")) {
            return FieldType.INTEGER;
        }
        else if(field.contains("float")) {
            return FieldType.FLOAT;
        }
        return null;
    }
}
