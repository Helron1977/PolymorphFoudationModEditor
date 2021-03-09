package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.api.LuaGenerator;
import helron.foundationwizard.com.datagenerator.ParamType;
import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedJList;
import helron.foundationwizard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;


public class RequestListType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestListType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        DefaultListModel<String> dataList = new DefaultListModel<>();
        ListenedJList listenedJList = new ListenedJList(dataList);
        listenedJList.setFixedCellWidth(300);
        formCLass.addComponentToColumnX(listenedJList,2,lineNumber);
        dataList.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                LuaGenerator lg = new LuaGenerator();
                List<String> list = new ArrayList<>();
                for (int i = 0; i<dataList.getSize() ; i++)
                    list.add(dataList.get(i));
                formCLass.inputs.put(parameter.getId(), lg.buildLuaList(list));
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {

            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });

        PlusButton plusButton = new PlusButton(parameter.getWantedDataStructureid(), ParamType.LIST, dataList);
        formCLass.addComponentToColumnX(plusButton,3,lineNumber);
        formCLass.getAddButtons().add(plusButton);
    }
}
