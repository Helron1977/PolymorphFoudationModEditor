package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.*;
import helron.foundationWizzard.com.ihm.ListenedJComboBox;
import helron.foundationWizzard.com.ihm.ListenedTextField;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Form extends JPanel {
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();

    String id;
    FormType formType;
    DataStructure dataStructure;
    DataStructureMap dataStructureMap;


    public Form(DataStructureMap dataStructureMap, String id, FormType formType) {
        this.id = id;
        this.formType = formType;
        this.dataStructure = dataStructureMap.getDataMap().get(id);
        this.dataStructureMap = dataStructureMap;

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
            //System.out.println(" ciciiiiii" +parameter.getId()+" "+parameter.getValue()+" "+ parameter.getType());
            cptRow++;
        }

        //addValidateButton(cptRow);

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

//        JTextField jtf = new JTextField(parameter.getValue());
//        setGridBagConstrains(lineNumber);
//        gb.setConstraints(jtf, gbc);
//        jtf.setEditable(false);
//        this.add(jtf);

        buildField(parameter, lineNumber);

    }

    public void buildField(Parameter parameter, int lineNumber) {
        if (parameter.getId().equals("DataType")) {
            JTextField jtf = new JTextField(dataStructure.getId());
            setGridBagConstrains(lineNumber);
            gb.setConstraints(jtf, gbc);
            jtf.setEditable(false);
            this.add(jtf);
        } else {
            if (parameter.getType() == ParamType.STRING) {
                ListenedTextField jt = new ListenedTextField(parameter.getType().getShortValue());
                setGridBagConstrains(lineNumber);
                gb.setConstraints(jt, gbc);
                this.add(jt);
                //                   inputs.put(label, defaultValue);

            } else if (parameter.getType() == ParamType.ASSET) {
                ListenedJComboBox<String> jbc = new ListenedJComboBox<>();
//                System.out.println(DataStructureType.ASSET.getPrefix()+parameter.getValue());
//                System.out.println(dataStructureMap.getAssetData(DataStructureType.ASSET.getPrefix()+parameter.getValue()));


                    List<String> assetNames = dataStructureMap.getAssetData(DataStructureType.ASSET.getPrefix()+parameter.getValue()).getAssetNames();
                    for (String value : assetNames) {
                        jbc.addItem(value);
                    }
//
//                    if (parameter.getValue() != null) {
//                        jbc.addItem(parameter.getValue());
//                        jbc.setSelectedItem(parameter.getValue());
//                    }
//                    gbc.gridx = 1;
//                    gbc.gridwidth = 1;
//                    gbc.gridy = lineNumber;
//                    gbc.fill = GridBagConstraints.BOTH;
//                    gb.setConstraints(jbc, gbc);
//                    this.add(jbc);
//                }
            }
        }
    }

    private void setGridBagConstrains(int lineNumber) {
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = lineNumber;
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
}

