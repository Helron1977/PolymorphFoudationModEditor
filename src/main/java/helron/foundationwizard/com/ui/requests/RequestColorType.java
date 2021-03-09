package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.JColorPicker;
import helron.foundationwizard.com.ui.customcomponents.JColorPickerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RequestColorType implements Requestable {

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestColorType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        Vector<String> columnTitle = new Vector<>();
        columnTitle.addElement("X");
        columnTitle.addElement("Y");
        columnTitle.addElement("Z");
        columnTitle.addElement("W");

        Vector<Integer> row = new Vector<>();
        row.add(0);
        row.add(0);
        row.add(0);
        row.add(0);

        Vector<Vector<Integer>> data = new Vector<>();
        data.addElement(row);

        JTable table = new JTable(data, columnTitle);
        JColorPicker jcolorPIcker = new JColorPicker();

        jcolorPIcker.setHSBControlsVisible(false);
        jcolorPIcker.setHexControlsVisible(false);
        //jcolorPIcker.setMode(JColorPicker.HUE);
        jcolorPIcker.setOpacityVisible(true);
        jcolorPIcker.setPreferredSize(new Dimension(400,400));

        formCLass.addComponentToColumnX(table,2,lineNumber);
        formCLass.addComponentToColumnX(jcolorPIcker, 3,lineNumber);

        jcolorPIcker.addPropertyChangeListener(e -> {
            List<String> dataList = new ArrayList<>();
            Color color = jcolorPIcker.getColor();
            float red = color.getRed()/255f;
            float green = color.getGreen()/255f;
            float blue = color.getBlue()/255f;
            table.getModel().setValueAt((String.valueOf(red)),0, 0);
            table.getModel().setValueAt((String.valueOf(green)),0, 1);
            table.getModel().setValueAt((String.valueOf(blue)),0, 2);
            table.getModel().setValueAt((String.valueOf(jcolorPIcker.getOpacity())),0, 3);

            dataList.add(String.valueOf(red));
            dataList.add(String.valueOf(green));
            dataList.add(String.valueOf(blue));
            dataList.add(String.valueOf(jcolorPIcker.getOpacity()));

            LuaGenerator luaGenerator = new LuaGenerator();
            parameter.setInput(luaGenerator.buildLuaList(dataList));
            formCLass.inputs.put(parameter.getId(), luaGenerator.buildLuaList(dataList) );
        });


    }
}
