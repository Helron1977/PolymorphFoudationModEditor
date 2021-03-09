package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedTextField;
import helron.foundationwizard.com.ui.customcomponents.PlusButton;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class RequestClassType implements Requestable{


    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestClassType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        JTextField listenedTextField = new ListenedTextField(null);
        listenedTextField.addActionListener(e -> {
            formCLass.inputs.put(parameter.getId(), listenedTextField.getText());
            parameter.setInput(listenedTextField.getText());
        });
        listenedTextField.setEditable(false);
        formCLass.addComponentToColumnX(listenedTextField,2,lineNumber);
        PlusButton plusButton = new PlusButton(new Scanner(parameter.getDescription()).next());
        //ajoute le bouton dans la colonne 3
        formCLass.addComponentToColumnX(plusButton,3,lineNumber);
        //ajoute le bouton a la liste des boutons plus dans form
        formCLass.getAddButtons().add(plusButton);

    }

}

