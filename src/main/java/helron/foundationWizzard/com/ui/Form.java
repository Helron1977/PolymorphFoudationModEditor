package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureClass;
import helron.foundationWizzard.com.datagenerator.Parameter;

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


    public Form(DataStructure dataStructure, FormType formType) {
        this.id = dataStructure.getId();
        this.formType = formType;

        setLayout(gb);
        setBackground(Color.white);
        setSize(dim);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
        setOpaque(false);

        if (formType.equals(FormType.CLASS)) {
            List<Parameter> parameterList = ((DataStructureClass)dataStructure).getParamList();
            buildEachRows(parameterList);

        }

    }

    private void buildEachRows(List<Parameter> parameterList) {
        int cptRow = 0;
        int finalCptRow = cptRow;
        for(Parameter parameter : parameterList){
            buildFormRow(parameter, finalCptRow);
            //System.out.println(parameter.getId()+" "+parameter.getValue()+" "+ parameter.getType());
        }
        cptRow++;

        //addValidateButton(cptRow);

    }

    public void buildFormRow(Parameter parameter, int lineNumber){
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
        gbc.gridy= lineNumber;
        gbc.fill= GridBagConstraints.BOTH;
        gb.setConstraints(lbl, gbc);
        this.add(lbl);

        buildField(parameter, lineNumber);

    }

    public void buildField(Parameter parameter, int lineNUmber){


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
