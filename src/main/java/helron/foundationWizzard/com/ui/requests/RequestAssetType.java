package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedJComboBox;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;

public class RequestAssetType implements Requestable{

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestAssetType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        ListenedJComboBox<String> jbc = new ListenedJComboBox<>();
        for ( String value : parameter.getValues()) {
            jbc.addItem(value);
        }
        form.addComponentToColumnX(jbc,2,lineNumber);
        JButton plusButton = new PlusButton(parameter.getDefaultValue());
        form.addComponentToColumnX(plusButton,3,lineNumber);
        form.getAddButtons().add(plusButton);
    }
}
