package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.JScrollTableCellEditor;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RequestVec3fType implements Requestable{

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestVec3fType();
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

        Vector<Vector<Integer>> data = new Vector<>();
        data.addElement(row);

        JTable table = new JTable(data, columnTitle);

        formCLass.addComponentToColumnX(table,2,lineNumber);

        table.getModel().addTableModelListener(e -> {
            List<String> dataList = new ArrayList<>();
            for ( int i = 0 ; i < 3 ; i++)
                dataList.add(table.getModel().getValueAt(0,i).toString());
            LuaGenerator luaGenerator = new LuaGenerator();
            parameter.setInput(luaGenerator.buildLuaList(dataList));
            formCLass.inputs.put(parameter.getId(), luaGenerator.buildLuaList(dataList) );
        });

    }
}
