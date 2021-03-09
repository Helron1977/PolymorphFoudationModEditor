package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.JScrollTableCellEditor;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RequestVect3iType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestVec3iType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        Vector<String> columnTitle = new Vector<>();
        columnTitle.addElement("X");
        columnTitle.addElement("Y");
        columnTitle.addElement("z");

        Vector<Integer> row = new Vector<>();
        row.add(0);
        row.add(0);
        row.add(0);

        Vector<Vector<Integer>> data = new Vector<>();
        data.addElement(row);

        JTable table = new JTable(data, columnTitle);

        TableColumnModel model = table.getColumnModel();
        for( int i = 0; i < 3; i++ ){
            model.getColumn(i).setCellEditor(new JScrollTableCellEditor());
        }

        formCLass.addComponentToColumnX(table,2,lineNumber);

        table.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                List<String> dataList = new ArrayList<>();
                for ( int i = 0 ; i < 3 ; i++)
                    dataList.add(table.getCellEditor(0,i).getCellEditorValue().toString());
                LuaGenerator luaGenerator = new LuaGenerator();
                parameter.setInput(luaGenerator.buildLuaList(dataList));
                formCLass.inputs.put(parameter.getId(), luaGenerator.buildLuaList(dataList) );
            }
        });


    }
}
