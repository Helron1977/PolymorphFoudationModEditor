package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        System.out.print(listenedTextField.getText());
        parameter.setInput(listenedTextField.getText());
        listenedTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                formCLass.inputs.put(parameter.getId(), listenedTextField.getText());
                parameter.setInput(listenedTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
}
