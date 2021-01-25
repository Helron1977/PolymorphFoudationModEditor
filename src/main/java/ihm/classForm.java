package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class classForm extends JFrame {
    private static GridBagLayout gb = new GridBagLayout();
    private static GridBagConstraints gbc = new GridBagConstraints();
    private ApiStructuresExtractor structure;
    private static Pattern cap = Pattern.compile("([A-Z]\\w+)");
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public classForm(ApiStructuresExtractor structure) throws HeadlessException {
        super("Foundation Wizard");
        this.structure = structure;
        buildForm(structure);
    }

    private void buildForm(ApiStructuresExtractor structures) {
        JPanel panel = new JPanel();

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        setContentPane(panel);

        setLayout(gb);
        setSize(dim);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
        setVisible(true);

        LinkedHashMap<String, String> params = structures.extractClass("BUILDING");
        buildEachRows(params);
        repaint();


    }

    private void buildEachRows(LinkedHashMap<String, String> params) {
        int cptRow = 0;
        for( String param : params.keySet()){
            String fullParamDescription = params.get(param);
            String paramType = structure.extractParamType(fullParamDescription);
            String defaultValue = structure.extractParamDefaultValue(fullParamDescription);
            buildFormRow(param, paramType, cptRow, defaultValue);
            cptRow++;
        }
    }

    private void buildFormRow(String label, String field, int lineNumber, String defaultValue) {
        JLabel lbl = new JLabel(label);
        System.out.println( "je suis le field"+field);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy= lineNumber;
        gbc.fill= GridBagConstraints.BOTH;
        gb.setConstraints(lbl, gbc);
        getContentPane().add(lbl);

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
                getContentPane().add(jt);
                break;
            case 2:
                JComboBox<String> jbc = new JComboBox<String>();
                if (structure.isAsset(field)) {
                    for (String value : structure.assetToList(field)) {
                        jbc.addItem(value);
                    }
                    System.out.println("defaut:" + structure.extractParamDefaultValue(field));
                    if(defaultValue != null){
                        jbc.addItem(defaultValue);
                        jbc.setSelectedItem(defaultValue);
                    }
                    gbc.gridx = 1;
                    gbc.gridwidth = 1;
                    gbc.gridy = lineNumber;
                    gbc.fill = GridBagConstraints.BOTH;
                    gb.setConstraints(jbc, gbc);
                    getContentPane().add(jbc);
                    break;
                } else if (structure.isEnum(field)) {
                    for (String value : structure.enumToList(field)) {
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
                    getContentPane().add(jbc);
                    break;
                }
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
                getContentPane().add(jcb);
                break;
            case 5:
                JSpinner js = new JSpinner();
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy = lineNumber;
                gbc.fill = GridBagConstraints.BOTH;
                gb.setConstraints(js, gbc);
                getContentPane().add(js);
                break;
        }
    }

    private int identifyType(String field) {

        if(field.contains("string")){
            return 1;
        }
        else if(field.equals(field.toUpperCase())){
            System.out.println(field);
            return 2;
        }
        else if(field.contains("list<")){
            System.out.println(field);
            return 3;
        }
        else if(field.contains("boolean")) {
            System.out.println("bool");
            return 4;
        }
        else if(field.contains("integer")) {
            System.out.println("integer");
            return 5;
        }
        return 6;
    }
}
