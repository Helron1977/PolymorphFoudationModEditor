package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureClass;
import helron.foundationWizzard.com.datagenerator.DataStructureMap;
import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.customcomponents.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JPanel {
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();

    protected String id;
    private FormType formType;
    private final DataStructure dataStructure;
    private final List<JButton> addButtons;
    private JButton validateButton;


    public Form(DataStructureMap dataStructureMap, String id, FormType formType) {
        this.id = id;
        this.formType = formType;
        this.dataStructure = dataStructureMap.getDataMap().get(id);
        this.addButtons = new ArrayList<>();

        setLayout(gb);
        setBackground(Color.white);
        setSize(dim);
        setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
        setOpaque(false);

        if (formType.equals(FormType.CLASS)) {
            List<Parameter> parameterList = ((DataStructureClass) dataStructure).getParamList();
            buildEachRows(parameterList);
        }

    }

    private void buildEachRows(List<Parameter> parameterList) {
        int cptRow = 0;
        for (Parameter parameter : parameterList) {
            buildFormRow(parameter, cptRow);
            cptRow++;
        }

        addValidateButton(cptRow);

    }

    public void buildFormRow(Parameter parameter, int lineNumber) {
        JTextField lbl = new JTextField(parameter.getId());
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
        gbc.gridy = lineNumber;
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(lbl, gbc);
        this.add(lbl);

        buildField(parameter, lineNumber);

    }

    public void buildField(Parameter parameter, int lineNumber) {
        if (parameter.getId().equals("DataType")) {
            JTextField jTextField = new JTextField(dataStructure.getId());
            addComponentToColumnX(jTextField,2,lineNumber);

        } else if (parameter.requestStringType()){

            ListenedTextField listenedTextField = new ListenedTextField(parameter.getType().getShortValue());
            addComponentToColumnX(listenedTextField,2,lineNumber);

            //                   inputs.put(label, defaultValue); TODO gerer les input via listener sur les champs depuis FormContainer

        } else if (parameter.requestEnumType()) {
            ListenedJComboBox<String> listenedJComboBox = new ListenedJComboBox<>();
            for ( String value : parameter.getValues())
                listenedJComboBox.addItem(value);

            addComponentToColumnX(listenedJComboBox,2,lineNumber);
            JButton plusButton = new PlusButton();
            addComponentToColumnX(plusButton,3, lineNumber);
            addButtons.add(plusButton);

        } else if (parameter.requestAssetType()){
            ListenedJComboBox<String> jbc = new ListenedJComboBox<>();
            for ( String value : parameter.getValues()) {
                jbc.addItem(value);
            }
            addComponentToColumnX(jbc,2,lineNumber);
            JButton plusButton = new PlusButton();
            addComponentToColumnX(plusButton,3,lineNumber);
            addButtons.add(plusButton);


        } else if (parameter.requestBooleanType()){
            JCheckBox listenedJCheckBox = new ListenedJCheckBox();
            if (parameter.getDefaultValue()!= null) {
                if (parameter.getDefaultValue().equals("true"))
                    listenedJCheckBox.setSelected(true);
                else if (parameter.getDefaultValue().equals("false"))
                    listenedJCheckBox.setSelected(false);
            }
            addComponentToColumnX(listenedJCheckBox,2,lineNumber);


        } else if (parameter.requestIntegerType()) {
            JSpinner listenedJSpinner= new ListenedJSpinner();
            addComponentToColumnX(listenedJSpinner,2,lineNumber);


        } else if (parameter.requestListType()) {
            DefaultListModel<String> stringList = new DefaultListModel<>();
            ListenedJList listenedJList = new ListenedJList(stringList);
            addComponentToColumnX(listenedJList,2,lineNumber);
            JButton plusButton = new PlusButton();
            addComponentToColumnX(plusButton,3,lineNumber);
            addButtons.add(plusButton);


        } else if (parameter.requestClassType()){
            JTextField listenedTextField = new ListenedTextField(null);
            listenedTextField.setEditable(false);
            addComponentToColumnX(listenedTextField,2,lineNumber);
        }
    }


    /**
     * Set a GridBagConstraints on the second column of a table.
     * Line number a iterate by a int
     * @param lineNumber line iterator
     */
    private void addComponentToColumnX(JComponent component, int columnNumber, int lineNumber) {
        gbc.gridx = columnNumber - 1;
        gbc.gridwidth = 1;
        gbc.gridy = lineNumber;
        gb.setConstraints(component, gbc);
        this.add(component);
    }

    private void addValidateButton(int lineNumber) {
        this.validateButton = new ValidateButton(this);
        gbc.gridx = 7;
        gbc.gridwidth = 0;
        gbc.gridy = lineNumber+1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gb.setConstraints(validateButton, gbc);
        this.add(validateButton);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public DataStructure getData() {
        return dataStructure;
    }

    public List<JButton> getAddButtons() {
        return addButtons;
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public void setValidateButton(JButton validateButton) {
        this.validateButton = validateButton;
    }
}

