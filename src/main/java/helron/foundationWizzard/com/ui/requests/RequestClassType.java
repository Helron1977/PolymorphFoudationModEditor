package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;
import helron.foundationWizzard.com.ui.customcomponents.ListenedTextField;
import helron.foundationWizzard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import java.util.Scanner;

public class RequestClassType implements Requestable{
    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestClassType();
    }

    @Override
    public void action(Form form, Parameter parameter, int lineNumber) {
        JTextField listenedTextField = new ListenedTextField(null);
        listenedTextField.setEditable(false);
        form.addComponentToColumnX(listenedTextField,2,lineNumber);
        PlusButton plusButton = new PlusButton(new Scanner (parameter.getDescription()).next());
        form.addComponentToColumnX(plusButton,3,lineNumber);
        form.getAddButtons().add(plusButton);
    }
}
