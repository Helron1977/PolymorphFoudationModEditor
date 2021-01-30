package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class MyPane extends JPanel {
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final MyTabs tabs;
    private final ApiStructuresExtractor structures;
    private final LinkedHashMap<String, String> userInput = new LinkedHashMap<>();

    public MyPane(String class_ID, ApiStructuresExtractor structures, MyTabs tabs) {
        this.structures = structures;
        this.tabs = tabs;

            setLayout(gb);
            setSize(dim);
            setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params);

    }

    private void addValidateButton(int lineNumber) {
        ValidateButton bt = new ValidateButton();
        gbc.gridx = 7;
        gbc.gridwidth = 0;
        gbc.gridy = lineNumber+1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gb.setConstraints(bt, gbc);
        this.add(bt);

    }

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

    private void addEditorPane(int lineNumber) {
        MyEditorJtext ejt = new MyEditorJtext("testsdjfk qjdsfmkdk,qsd, va,k ,zk\n sjdkqsdk ql");
        gbc.gridx = 4;
        gbc.gridwidth = 2;
        gbc.gridy = lineNumber;
        gbc.gridheight = lineNumber;
        gbc.fill = GridBagConstraints.REMAINDER;
        gb.setConstraints(ejt, gbc);
    }

    private void buildFormRow(String label, String field, int lineNumber, String defaultValue) {

        JLabel lbl = new JLabel(label);
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridy= lineNumber;
            gbc.fill= GridBagConstraints.BOTH;
            gb.setConstraints(lbl, gbc);
        this.add(lbl);

        buildField(field, lineNumber, defaultValue);
    }

    private void buildField(String field, int lineNumber, String defaultValue) {
        int paramType = identifyType(field);

        switch (paramType) {
            case 1:
                JTextField jt = new JTextField(field);
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy = lineNumber;
                gb.setConstraints(jt, gbc);
                this.add(jt);
                break;
            case 2:
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

                    PlusButton bt = new PlusButton(structures, field, tabs);
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
                    if(defaultValue != null){
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
            case 3:
                field = field .replaceAll("list<","")
                              .replaceAll(">","");

                JTextField jtf = new JTextField();
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jtf, gbc);
                this.add(jtf);

                PlusButton bt = new PlusButton(structures, field, tabs);
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.REMAINDER;
                    gb.setConstraints(bt, gbc);
                this.add(bt);

            case 4:
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
                break;
            case 5:
                JSpinner js = new JSpinner();
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(js, gbc);
                this.add(js);
                break;
            case 6:
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

    private int identifyType(String field) {

        if(field.contains("string")){
            return 1;
        }
        else if(field.equals(field.toUpperCase())){
            return 2;
        }
        else if(field.contains("list<")){
            return 3;
        }
        else if(field.contains("boolean")) {
            return 4;
        }
        else if(field.contains("integer")) {
            return 5;
        }
        else if(field.contains("float")) {
            return 6;
        }
        return 0;
    }
}
