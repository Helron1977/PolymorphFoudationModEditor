package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedJComboBox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class RequestEnumType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestEnumType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        ListenedJComboBox<String> listenedJComboBox = new ListenedJComboBox<>();
        for ( String value : parameter.getValues())
            listenedJComboBox.addItem(value);
        parameter.setInput(listenedJComboBox.getSelectedItem().toString());
        listenedJComboBox.addItemListener(e -> formCLass.inputs.put(parameter.getId(), Objects.requireNonNull(listenedJComboBox.getSelectedItem()).toString()));

        formCLass.addComponentToColumnX(listenedJComboBox,2,lineNumber);
    }
}
