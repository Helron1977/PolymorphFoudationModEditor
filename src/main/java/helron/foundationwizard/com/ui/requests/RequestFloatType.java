package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedJSpinner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RequestFloatType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestFloatType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        JSpinner listenedJSpinner= new ListenedJSpinner();
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0,-100,100,0.1);
        listenedJSpinner.setModel(spinnerNumberModel);

        formCLass.addComponentToColumnX(listenedJSpinner,2,lineNumber);
        parameter.setInput(listenedJSpinner.getValue().toString());
        listenedJSpinner.addChangeListener(e -> {
            formCLass.inputs.put(parameter.getId(), listenedJSpinner.getValue().toString());
            parameter.setInput(listenedJSpinner.getValue().toString());
        });

    }
}
