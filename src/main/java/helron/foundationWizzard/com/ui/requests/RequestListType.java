package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.api.LuaGenerator;
import helron.foundationWizzard.com.datagenerator.ParamType;
import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.FormCLass;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJList;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

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
