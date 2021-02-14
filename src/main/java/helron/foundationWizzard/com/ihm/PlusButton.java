/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import helron.foundationWizzard.com.ui.FormsContainer;

import javax.swing.*;

public class PlusButton extends JButton {
    public PlusButton(ListenedJComboBox<String> jbc, ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                FormRequestDialogForClass rq = new FormRequestDialogForClass(jbc, structures, class_ID, tabs);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
    public PlusButton(ApiStructuresExtractor structures, String classID, FormsContainer tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                Form form= new Form(classID, structures, tabs);
                form.setOpaque(false);
                tabs.add(classID,form);
                tabs.setTabComponentAt(tabs.indexOfTab(classID),tabs.getTitlePanel( form, classID));

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
    public PlusButton(DefaultListModel<String> listData, JList<String> jList, ApiStructuresExtractor structures, String class_ID, FormsContainer tabs) {
        super("+");
        this.addActionListener(e -> {
            try {
                FormRequestDialogForSetList rq = new FormRequestDialogForSetList(listData,jList,structures, class_ID, tabs);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
*/
