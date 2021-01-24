package ihm;

import api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;
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
            String paramType = structure.extractParamType(params.get(param));
            buildFormRow(param, paramType, cptRow);
            cptRow++;
        }
    }

    private void buildFormRow(String label, String field, int lineNumber) {
        JLabel lbl = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy= lineNumber;
        gbc.fill= GridBagConstraints.BOTH;
        gb.setConstraints(lbl, gbc);
        getContentPane().add(lbl);


        buildField(field, lineNumber);



    }

    private void buildField(String field, int lineNumber) {
        int paramType = identifyType(field);

        switch (paramType){
            case 1 :         JTextField jt = new JTextField(field);
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy= lineNumber;
                gb.setConstraints(jt, gbc);
                getContentPane().add(jt);
            case 2:          JComboBox jbc = new JComboBox();

                jbc.addItem("test");
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                gbc.gridy= lineNumber;
                gb.setConstraints(jbc, gbc);
                getContentPane().add(jbc);
        }
    }

    private int identifyType(String field) {
        Matcher m = cap.matcher(field);
        boolean match = m.matches();

        if(field.contains("string")){
            return 1;
        }
        else if(match)
            return 2;
        return 0;
    }
}
