package helron.foundationwizard.com.ui;

import helron.foundationwizard.com.datagenerator.DataStructure;
import helron.foundationwizard.com.datagenerator.DataStructureClass;
import helron.foundationwizard.com.datagenerator.DataStructureType;
import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.customcomponents.PlusButton;
import helron.foundationwizard.com.ui.customcomponents.ValidateButton;
import helron.foundationwizard.com.ui.requests.RequestsGenerator;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class FormCLass extends JPanel {
    public static final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
    private static final GridBagLayout gb = new GridBagLayout();
    private static final GridBagConstraints gbc = new GridBagConstraints();

    protected String id;
    private final DataStructure dataStructure;
    private final List<PlusButton> addButtons;
    public LinkedHashMap<String,String> inputs = new LinkedHashMap<>();
    JPanel body;
    JPanel leftPanel;
    DefaultListModel<String> dataList;
    private JButton validateButton;


    public FormCLass(DataStructure dataStructure, String id, DefaultListModel<String> dataList) {
        this.dataStructure = dataStructure;
        this.id = id;
        this.addButtons = new ArrayList<>();
        this.dataList = dataList;

        this.leftPanel = new JPanel();
        this.body = new JPanel();

        init();
    }

    private void init() {

        initLeftPanel();
        leftPanel.setOpaque(false);
        this.add(leftPanel);

        initBody();
        setOpaque(false);
        this.add(body);
    }

    private void initLeftPanel() {
        if (Objects.nonNull(dataList)){
            DefaultListModel<String> test = new DefaultListModel<>();
            JList<String> list = new JList<>();
                list.setFixedCellWidth(300);
            test.addElement("Add an element");
            dataList.addListDataListener(new ListDataListener() {
                @Override
                public void intervalAdded(ListDataEvent e) {
                    test.removeAllElements();

                    for (int i = 0; i< dataList.getSize(); i++){
                        test.addElement(dataList.getElementAt(i));
                    }
                    list.setFixedCellWidth(300);
                }

                @Override
                public void intervalRemoved(ListDataEvent e) {

                }

                @Override
                public void contentsChanged(ListDataEvent e) {

                }
            });
            list.setModel(test);
            list.setBackground(Color.WHITE);
            list.setVisible(true);
            leftPanel.add(list);
        }
    }

    private void initBody(){
        body.setLayout(gb);
        body.setBackground(Color.white);
        body.setSize(DIM);
        body.setLocation(DIM.width / 2 - getWidth() / 2, DIM.height / 2 - getHeight() / 2);
        body.setOpaque(false);

        if (dataStructure.getDataStructureType().equals(DataStructureType.CLASS)){
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
        body.add(lbl);

        buildField(parameter, lineNumber);

    }

    public void buildField(Parameter parameter, int lineNumber) {

        if (parameter.getId().equals("DataType")) {
            addDatatypeField(parameter, lineNumber);

        } else {
            RequestsGenerator requests = new RequestsGenerator();
            requests.getRequestsList().forEach(requestable -> {
                if (requestable.isRequired(parameter)) {
                    try {
                        requestable.action(this, parameter, lineNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputs.put(parameter.getId(), parameter.getDefaultValue());
                    System.out.println(parameter.getId() + " " + parameter.getDefaultValue());
                }
            });
        }

    }

    private void addDatatypeField(Parameter parameter, int lineNumber) {
        JTextField jTextField = new JTextField(parameter.getDescription());
        jTextField.setBackground(new Color(0x22471D));
        jTextField.setForeground(Color.WHITE);
        addComponentToColumnX(jTextField,2,lineNumber);
        inputs.put(parameter.getId(), parameter.getDescription());
        parameter.setInput(parameter.getDescription());
        System.out.println(parameter.getInput());
    }


    /**
     * Set a GridBagConstraints on the second column of a table.
     * Line number a iterate by a int
     * @param lineNumber line iterator
     */
    public void addComponentToColumnX(JComponent component, int columnNumber, int lineNumber) {
        gbc.gridx = columnNumber - 1;
        gbc.gridwidth = 1;
        gbc.gridy = lineNumber;
        gb.setConstraints(component, gbc);
        body.add(component);
    }

    public void addValidateButton(int lineNumber) {
        this.validateButton = new ValidateButton(this);
        gbc.gridx = 7;
        gbc.gridwidth = 0;
        gbc.gridy = lineNumber+1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gb.setConstraints(validateButton, gbc);
        body.add(validateButton);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public DataStructure getData() {
        return dataStructure;
    }

    public List<PlusButton> getAddButtons() {
        return addButtons;
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public void setValidateButton(JButton validateButton) {
        this.validateButton = validateButton;
    }

    public LinkedHashMap<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(LinkedHashMap<String, String> inputs) {
        this.inputs = inputs;
    }
}

