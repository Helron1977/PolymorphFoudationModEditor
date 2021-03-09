package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedTextField;
import helron.foundationwizard.com.ui.customcomponents.irregularpolygons.EditorContainer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

public class RequestPolygonType implements Requestable {

    private Parameter parameter;

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestPolygonType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) throws IOException {
        JTextArea field = new JTextArea();
        formCLass.addComponentToColumnX(field, 2, lineNumber);
        EditorContainer editorContainer = new EditorContainer();
        formCLass.addComponentToColumnX(editorContainer,3,lineNumber);

        editorContainer.getConsole().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                field.setText(editorContainer.getConsole().getConsoleText());
                parameter.setInput(editorContainer.getConsole().getConsoleText());
//todo bind correctly the field and the value of the console panel
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
