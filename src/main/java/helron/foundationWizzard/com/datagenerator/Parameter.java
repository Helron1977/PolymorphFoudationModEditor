package helron.foundationWizzard.com.datagenerator;

import java.util.List;

public class Parameter {

    private String id;
    private ParamType type;
    private String description;
    private String defaultValue;
    private List<String> values;

    public Parameter(String id) {
        this.id = id;
    }

    public boolean requestEnumType(){
        return type==ParamType.ENUM;
    }

    public boolean requestAssetType(){
        if (!id.equals("DataType"))
        return type==ParamType.ASSET;
        else
            return type==ParamType.DATATYPE;
    }

    public boolean isClassType(){
        return type==ParamType.CLASS;
    }

    public boolean isListType(){
        return type==ParamType.LIST;
    }

    public boolean isIntegerType(){
        return type==ParamType.INTEGER;
    }

    public boolean isFloatType(){
        return type==ParamType.FLOAT;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParamType getType() {
        return type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public boolean requestBooleanType() {
        return type == ParamType.BOOLEAN;
    }

    public boolean requestIntegerType() {
        return type == ParamType.INTEGER;
    }

    public boolean requestStringType() {
        return type== ParamType.STRING;
    }

    public boolean requestListType() {
        return type== ParamType.LIST;
    }

    public boolean requestClassType() {
        return type == ParamType.CLASS;
    }
}
