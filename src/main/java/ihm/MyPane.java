package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class MyPane extends JPanel {
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final MyTabs tabs;
    private final ApiStructuresExtractor structures;

    public MyPane(String class_ID, ApiStructuresExtractor structures, MyTabs tabs) {
        this.structures = structures;
        this.tabs = tabs;

            setLayout(gb);
            setSize(dim);
            setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        LinkedHashMap<String, String> params = structures.extractClass(class_ID);
        buildEachRows(params,this);
    }
    private void buildEachRows(LinkedHashMap<String, String> params, JPanel panel) {
        int cptRow = 0;
        for( String param : params.keySet()){
            String fullParamDescription = params.get(param);
            String paramType = structures.extractParamType(fullParamDescription);
            String defaultValue = structures.extractParamDefaultValue(fullParamDescription);

            buildFormRow(param, paramType, cptRow, defaultValue, panel);
            cptRow++;
        }
    }

    private void buildFormRow(String label, String field, int lineNumber, String defaultValue, JPanel panel) {

        JLabel lbl = new JLabel(label);
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridy= lineNumber;
            gbc.fill= GridBagConstraints.BOTH;
            gb.setConstraints(lbl, gbc);
        panel.add(lbl);

        buildField(field, lineNumber, defaultValue, panel);



    }

    private void buildField(String field, int lineNumber, String defaultValue, JPanel panel) {
        int paramType = identifyType(field);

        switch (paramType) {
            case 1:
                JTextField jt = new JTextField(field);
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy = lineNumber;
                gb.setConstraints(jt, gbc);
                panel.add(jt);
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
                    panel.add(jbc);

                    PlusButton bt = new PlusButton(structures, field, tabs);
                        gbc.gridx = 2;
                        gbc.gridwidth = 1;
                        gbc.gridy = lineNumber;
                        gbc.fill = GridBagConstraints.REMAINDER;
                        gb.setConstraints(bt, gbc);
                    panel.add(bt);

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
                    panel.add(jbc);
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
                panel.add(jtf);

                PlusButton bt = new PlusButton(structures, field, tabs);
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.REMAINDER;
                    gb.setConstraints(bt, gbc);
                panel.add(bt);

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
                panel.add(jcb);
                break;
            case 5:
                JSpinner js = new JSpinner();
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(js, gbc);
                panel.add(js);
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
        return 6;
    }
}
