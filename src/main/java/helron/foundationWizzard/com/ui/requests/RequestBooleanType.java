package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJCheckBox;

import javax.swing.*;

public class RequestBooleanType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestBooleanType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        JCheckBox listenedJCheckBox = new ListenedJCheckBox();
        if (parameter.getDefaultValue()!= null) {
            if (parameter.getDefaultValue().equals("true"))
                listenedJCheckBox.setSelected(true);
            else if (parameter.getDefaultValue().equals("false"))
                listenedJCheckBox.setSelected(false);
        }
        form.addComponentToColumnX(listenedJCheckBox,2,lineNumber);
    }
}
