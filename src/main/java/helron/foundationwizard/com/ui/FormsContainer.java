package helron.foundationwizard.com.ui;

import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.datagenerator.DataStructure;
import helron.foundationwizard.com.datagenerator.DataStructureMap;
import helron.foundationwizard.com.datagenerator.DataStructureType;
import helron.foundationwizard.com.ui.customcomponents.JColorPicker;
import helron.foundationwizard.com.ui.customcomponents.JColorPickerPanel;
import helron.foundationwizard.com.ui.customcomponents.LuaScriptFrame;
import helron.foundationwizard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Define a Panel that contains all forms.
 */
public class FormsContainer extends JTabbedPane {
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

    public void init(String classID, DataStructure dataStructure, DefaultListModel<String> dataList) {
        FormCLass formCLass = new FormCLass(dataStructure, classID, dataList);
            setBounds(40, 20, 300, 300);

        for (PlusButton plusButton : formCLass.getAddButtons()) {
            plusButton.addActionListener(e->addPanel(plusButton));
        }
        addValidateButton(formCLass, dataList);

        this.add(formCLass.id, formCLass);
    }


    private void addValidateButton(FormCLass formCLass, DefaultListModel<String> dataList) {

            formCLass.getValidateButton().addActionListener(e->{
                LuaGenerator scriptGenerator = new LuaGenerator(formCLass.getInputs());
                String script  = scriptGenerator.buildLuaTable();
                System.out.println(script);
                if (Objects.nonNull(dataList)) {
                    dataList.addElement(script);
                } else {

                    try {
                        LuaScriptFrame scriptFrame = new LuaScriptFrame(scriptGenerator);


                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
    }

    private void addPanel( PlusButton plusButton){


            String wantedStructureId= DataStructureType.CLASS.getPrefix()+plusButton.getStructureIdRequest();

            DataStructure wantedDataStructure = dataStructureMap.getDataMap().get(wantedStructureId);

            init(wantedStructureId,wantedDataStructure,plusButton.getDataList());

    }

}