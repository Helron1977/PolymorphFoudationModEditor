package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.FormCLass;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJCheckBox;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RequestBooleanType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestBooleanType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        JCheckBox listenedJCheckBox = new ListenedJCheckBox();
        if (parameter.getDefaultValue()!= null) {
            if (parameter.getDefaultValue().equals("true"))
                listenedJCheckBox.setSelected(true);
            else if (parameter.getDefaultValue().equals("false"))
                listenedJCheckBox.setSelected(false);
        }
        formCLass.inputs.put(parameter.getId(), parameter.getDefaultValue());
        listenedJCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (listenedJCheckBox.isSelected()){
                    formCLass.inputs.put(parameter.getId(), "true");
                    parameter.setInput("true");
                } else {
                    formCLass.inputs.put(parameter.getId(), "false");
                    parameter.setInput("false");
                }
            }
        });
        formCLass.addComponentToColumnX(listenedJCheckBox,2,lineNumber);
    }
}
