package helron.foundationWizzard.com.ui;

import helron.foundationWizzard.com.datagenerator.DataStructure;

import javax.swing.*;

public class Form {
    String id;
    FormType formType;
    DataStructure dataStructure;
    private JPanel form;

    public Form(DataStructure dataStructure, FormType formType) {
        this.id = dataStructure.getId();
        this.formType = formType;

        this.form = new JPanel();
        form.setOpaque(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public DataStructure getData() {
        return dataStructure;
    }

    public JPanel getForm() {
        return form;
    }

}
