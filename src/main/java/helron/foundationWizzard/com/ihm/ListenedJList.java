package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.LuaGenerator;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListenedJList extends JList<String>{
    private final DefaultListModel<String> dataList;
    private String lbl;

    public ListenedJList(DefaultListModel<String> listData, String label) {
        super(listData);
        this.dataList = listData;
        dataList.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                List<String> listToScript= new ArrayList<>();
                for(int i = 0; i < dataList.getSize(); i++){
                    listToScript.add(dataList.getElementAt(i));
                }
                setBackground(new Color(0xAFF3C1));
                LuaGenerator lg = new LuaGenerator(Form.inputs);
                String aLuaList = lg.buildLuaList(listToScript);
                Form.inputs.put(lbl,aLuaList);

            }

            @Override
            public void intervalRemoved(ListDataEvent e) {

            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });
        this.lbl = label;
    }

}
