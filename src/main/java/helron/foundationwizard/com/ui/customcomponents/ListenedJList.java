package helron.foundationwizard.com.ui.customcomponents;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListenedJList extends JList<String>{
    private final DefaultListModel<String> dataList;

    public ListenedJList(DefaultListModel<String> listData) {
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
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {

            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });
    }

}
