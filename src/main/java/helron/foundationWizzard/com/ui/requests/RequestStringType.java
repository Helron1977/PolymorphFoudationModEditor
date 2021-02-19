package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedTextField;

public class RequestStringType extends RequestsGenerator implements Requestable{


    @Override
    public boolean condition(Parameter parameter) {
        return parameter.requestStringType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        ListenedTextField listenedTextField = new ListenedTextField(parameter.getType().getShortValue());
        form.addComponentToColumnX(listenedTextField,2,lineNumber);
    }
}
