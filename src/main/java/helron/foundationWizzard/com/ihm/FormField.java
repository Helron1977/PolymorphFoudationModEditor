package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.util.Objects;

public class FormField{
    private FieldType fieldType;
    private String defaultValue;


    public static JComponent FormFieldBuilder(String paramName, String defaultFieldText, ApiStructuresExtractor ApiStructures) {
        FieldType fieldType = identifyType(paramName);

        switch (Objects.requireNonNull(fieldType)) {
            case DATATYPE:
                return  new JTextField(paramName);

            case ID:
                return  new JTextField(paramName);

            case STRING:
                return  new JTextField(defaultFieldText);

            case ASSET_ENUM:
                JComboBox<String> jbc = new JComboBox<String>();
                if (ApiStructures.isAsset(paramName)) {
                    for (String value : ApiStructures.assetToList(paramName)) {
                        jbc.addItem(value);
                    }

                    if (defaultFieldText != null) {
                        jbc.addItem(paramName);
                        jbc.setSelectedItem(paramName);
                    }
                    return jbc;


                } else if (ApiStructures.isEnum(paramName)) {
                    for (String value : ApiStructures.EnumToList(paramName)) {
                        jbc.addItem(value);
                    }
                    if (defaultFieldText != null) {
                        jbc.setSelectedItem(paramName);
                    }
                    return jbc;
                }
            case LIST:
                paramName = paramName.replaceAll("list<", "")
                        .replaceAll(">", "");

                DefaultListModel<String> listData = new DefaultListModel<>();
                return new JList<String>(listData);


            case BOOLEAN:
                JCheckBox jcb = new JCheckBox(paramName);
                if (defaultFieldText != null) {
                    if (paramName.equals("true"))
                        jcb.setSelected(true);
                    else if (paramName.equals("false"))
                        jcb.setSelected(false);
                }
                return jcb;

            case INTEGER:
                return new JSpinner();

            case FLOAT:
                float value = 0f;
                float min = 0f;
                float max = 100f;
                float step = 0.01f;

                SpinnerNumberModel myFloatSpinner = new SpinnerNumberModel(value, min, max, step);

                return new JSpinner(myFloatSpinner);
        }
        return null;
    }

    public static FieldType identifyType(String field) {

        //TODO add a sort by Polygon type
        if (field.contains("DataType")) {
            return FieldType.DATATYPE;
        }
        if (field.contains("Id")){
            return FieldType.ID;
        }
        if(field.contains("string")){
            return FieldType.STRING;
        }
        else if(field.equals(field.toUpperCase())){
            return FieldType.ASSET_ENUM;
        }
        else if(field.contains("list<")){
            return FieldType.LIST;
        }
        else if(field.contains("boolean")) {
            return FieldType.BOOLEAN;
        }
        else if(field.contains("integer")) {
            return FieldType.INTEGER;
        }
        else if(field.contains("float")) {
            return FieldType.FLOAT;
        }
        return null;
    }

/*    public LinkedHashMap<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(LinkedHashMap<String, String> inputs) {
        this.inputs = inputs;
    }*/
}

