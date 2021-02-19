package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJList;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;

public class RequestListType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestListType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        DefaultListModel<String> stringList = new DefaultListModel<>();
        ListenedJList listenedJList = new ListenedJList(stringList);
        form.addComponentToColumnX(listenedJList,2,lineNumber);
        JButton plusButton = new PlusButton(parameter.getDefaultValue());
        form.addComponentToColumnX(plusButton,3,lineNumber);
        form.getAddButtons().add(plusButton);
    }
}
