package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.FormCLass;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJSpinner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RequestIntegerType implements Requestable{

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestIntegerType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        JSpinner listenedJSpinner= new ListenedJSpinner();
        formCLass.addComponentToColumnX(listenedJSpinner,2,lineNumber);
        parameter.setInput(listenedJSpinner.getValue().toString());
        listenedJSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                formCLass.inputs.put(parameter.getId(), listenedJSpinner.getValue().toString());
            }
        });
    }
}
