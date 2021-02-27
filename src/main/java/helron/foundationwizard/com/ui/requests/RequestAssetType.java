package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;
import helron.foundationwizard.com.ui.customcomponents.ListenedJComboBox;
import helron.foundationwizard.com.ui.customcomponents.PlusButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Scanner;

public class RequestAssetType implements Requestable{

    @Override
    public boolean isRequired(Parameter parameter) {
        return parameter.requestAssetType();
    }

    @Override
    public void action(FormCLass formCLass, Parameter parameter, int lineNumber) {
        ListenedJComboBox<String> stringListenedJComboBox = new ListenedJComboBox<>();
        for ( String value : parameter.getValues()) {
            stringListenedJComboBox.addItem(value);
        }
        stringListenedJComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                formCLass.inputs.put(parameter.getId(), Objects.requireNonNull(stringListenedJComboBox.getSelectedItem()).toString());
                parameter.setInput(Objects.requireNonNull(stringListenedJComboBox.getSelectedItem()).toString());
            }
        });
        stringListenedJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formCLass.inputs.put(parameter.getId(), Objects.requireNonNull(stringListenedJComboBox.getSelectedItem()).toString());
                parameter.setInput(Objects.requireNonNull(stringListenedJComboBox.getSelectedItem()).toString());
            }
        });

        formCLass.addComponentToColumnX(stringListenedJComboBox,2,lineNumber);
        PlusButton plusButton = new PlusButton(new Scanner (parameter.getDescription()).next());
        formCLass.addComponentToColumnX(plusButton,3,lineNumber);
        formCLass.getAddButtons().add(plusButton);
    }
}
