package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.FormCLass;
import helron.foundationWizzard.com.ui.customcomponents.ListenedTextField;

public class RequestStringType implements Requestable{


    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestStringType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        ListenedTextField listenedTextField = new ListenedTextField(parameter.getType().getShortValue());
        formCLass.addComponentToColumnX(listenedTextField,2,lineNumber);
        formCLass.inputs.put(parameter.getId(), listenedTextField.getText());
        parameter.setInput(listenedTextField.getText());
    }
}
