package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.api.LuaGenerator;
import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureMap;
import helron.foundationWizzard.com.datagenerator.DataStructureType;
import helron.foundationWizzard.com.datagenerator.ParamType;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Define a Panel that contains all forms.
 */
public class FormsContainer extends JTabbedPane implements FormSelector{
    LinkedList<FormCLass> formCLassLinkedList;
    DataStructureMap dataStructureMap;

    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Build and set  FormsContainer object that is a JtabbedPane.
     * Instanciate a form set as a tab in the TabbedPane.
     * @param classID a Tab name
     * @param dataStructureMap the DataStructureMap source of the form to generate in this tab
     */
    public FormsContainer(String classID, DataStructureMap dataStructureMap, FormType formTypeRequest) throws IOException {
        this.dataStructureMap = dataStructureMap;

        DataStructure dataStructure = dataStructureMap.getDataMap().get(classID);

        init(classID, dataStructure, null);
    }

    private void init(String classID, DataStructure dataStructure, DefaultListModel<String> dataList) {
        FormCLass formCLass = new FormCLass(dataStructure, classID, dataList);
            setBounds(40, 20, 300, 300);

        for (PlusButton plusButton : formCLass.getAddButtons()) {
            plusButton.addActionListener(e->addPanel(plusButton));
        }
        addValidateButton(formCLass, dataList);

        this.add(formCLass.id, formCLass);
    }


    private void addValidateButton(FormCLass formCLass, DefaultListModel<String> dataList) {
        if (Objects.nonNull(dataList)){
            formCLass.getValidateButton().addActionListener(e->{
                LuaGenerator scriptGenerator = new LuaGenerator(formCLass.getInputs());
                String script  = scriptGenerator.buildLuaTable();
                dataList.addElement(script);
            });
        } else {
            formCLass.getValidateButton().addActionListener(e -> {
                LuaGenerator scriptGenerator = new LuaGenerator(formCLass.getInputs());
                String script = scriptGenerator.buildLuaTable();
                System.out.println(script);

            });
        }
    }

    private void addPanel( PlusButton plusButton){


        if (plusButton.getRequestedFormType()== ParamType.LIST){

            String wantedStructureId= DataStructureType.CLASS.getPrefix()+plusButton.getStructureIdRequest();

            DataStructure wantedDataStructure = dataStructureMap.getDataMap().get(wantedStructureId);

            init(wantedStructureId,wantedDataStructure,plusButton.getDataList());



        } else {
            String wantedStructureId= DataStructureType.CLASS.getPrefix()+plusButton.getStructureIdRequest();
            DataStructure wantedDataStructure = dataStructureMap.getDataMap().get(wantedStructureId);
            init(wantedStructureId,wantedDataStructure, null);
        }
    }
}