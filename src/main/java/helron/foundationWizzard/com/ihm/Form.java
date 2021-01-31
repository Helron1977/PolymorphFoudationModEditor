package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Objects;

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
    private final LinkedHashMap<String, String> userInput = new LinkedHashMap<>();
    private static final LinkedHashMap<String,String> inputs = new LinkedHashMap<>();

    /**
     * Build a Form
     * @param class_ID String, Name of the Asset to extract for witch a Form has to be build
     * @param structures ApiStructuresExtractor, the Data source.
     * @param formsContainer the tabbedPanel.
     */
    public Form(String class_ID, ApiStructuresExtractor structures, FormsContainer formsContainer) {
        this.structures = structures;
        this.formsContainer = formsContainer;

            setLayout(gb);
            setSize(dim);
            setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params);
        /*
        @test
         */
        for (String key : inputs.keySet()) {
            System.out.println(key+"="+ inputs.get(key));
        }

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
        lbl.setOpaque(true);
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
        FieldType fieldType = identifyType(field);

        switch (Objects.requireNonNull(fieldType)) {
            case STRING:
                JTextField jt = new JTextField(field);
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy = lineNumber;
                gb.setConstraints(jt, gbc);
                this.add(jt);
                inputs.put(label, jt.getText());
                break;
            case ASSET_ENUM:
                JComboBox<String> jbc = new JComboBox<>();
                if (structures.isAsset(field)) {
                    for (String value : structures.assetToList(field)) {
                        jbc.addItem(value);
                    }

                    if(defaultValue != null){
                        jbc.addItem(defaultValue);
                        jbc.setSelectedItem(defaultValue);
                    }
                        gbc.gridx = 1;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.BOTH;
                        gb.setConstraints(jbc, gbc);
                    this.add(jbc);

                    PlusButton bt = new PlusButton(structures, field, formsContainer);
                        gbc.gridx = 2;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.REMAINDER;
                        gb.setConstraints(bt, gbc);
                    this.add(bt);

                    inputs.put(label, Objects.requireNonNull(jbc.getSelectedItem()).toString());
                    break;
                } else if (structures.isEnum(field)) {
                    for (String value : structures.enumToList(field)) {
                        jbc.addItem(value);
                    }
                    if(defaultValue != null){
                        jbc.setSelectedItem(defaultValue);
                    }
                        gbc.gridx = 1;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.BOTH;
                        gb.setConstraints(jbc, gbc);
                    this.add(jbc);

                    inputs.put(label, Objects.requireNonNull(jbc.getSelectedItem()).toString());
                    break;
                }
            case LIST:
                field = field .replaceAll("list<","")
                              .replaceAll(">","");

                JTextField jtf = new JTextField();
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jtf, gbc);
                this.add(jtf);

                PlusButton bt = new PlusButton(structures, field, formsContainer);
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.REMAINDER;
                    gb.setConstraints(bt, gbc);
                this.add(bt);

                inputs.put(label, jtf.getText());
                break;

            case BOOLEAN:
                JCheckBox jcb = new JCheckBox();
                if(defaultValue != null){
                    if(defaultValue.equals("true"))
                        jcb.setSelected(true);
                    else if(defaultValue.equals("false"))
                        jcb.setSelected(false);
                }
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jcb, gbc);
                this.add(jcb);

                inputs.put(label, jcb.getText());
                break;
            case INTEGER:
                JSpinner js = new JSpinner();
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(js, gbc);
                this.add(js);

                inputs.put(label, js.getValue().toString());
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

                inputs.put(label, jfs.getValue().toString());
                break;
        }
    }

    /**
     * identify the param Type looking for substring
     * @param field String, the field to scan
     * @return int, an arbitrary value
     */
    private FieldType identifyType(String field) {

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
