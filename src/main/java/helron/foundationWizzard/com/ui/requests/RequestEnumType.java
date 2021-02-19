package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJComboBox;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;

public class RequestEnumType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestEnumType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        ListenedJComboBox<String> listenedJComboBox = new ListenedJComboBox<>();
        for ( String value : parameter.getValues())
            listenedJComboBox.addItem(value);

        form.addComponentToColumnX(listenedJComboBox,2,lineNumber);
        JButton plusButton = new PlusButton(parameter.getDescription());
        System.out.println(parameter.getDefaultValue() + " " + parameter.getDescription());
        form.addComponentToColumnX(plusButton,3, lineNumber);
        form.getAddButtons().add(plusButton);

    }
}
