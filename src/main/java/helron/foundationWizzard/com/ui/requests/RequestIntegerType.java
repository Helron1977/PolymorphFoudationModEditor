package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJSpinner;

import javax.swing.*;

public class RequestIntegerType implements Requestable{

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestIntegerType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        JSpinner listenedJSpinner= new ListenedJSpinner();
        form.addComponentToColumnX(listenedJSpinner,2,lineNumber);
    }
}
