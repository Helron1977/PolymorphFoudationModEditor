package helron.foundationwizard.com.datagenerator;

import java.util.List;

public class Parameter {

    private String id;
    private ParamType type;
    private String description;
    private String defaultValue;
    private String input;
    private List<String> values;
    private String wantedDataStructureid;

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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getWantedDataStructureid() {
        return wantedDataStructureid;
    }

    public void setWantedDataStructureid(String wantedDataStructureid) {
        this.wantedDataStructureid = wantedDataStructureid;
    }

    public boolean requestFloatType() {
        return  type == ParamType.FLOAT;
    }

    public boolean requestVec2iType() {
        return  type == ParamType.VEC2I;
    }

    public boolean requestVec3iType() {
        return type == ParamType.VEC3I;
    }

    public boolean requestVec2fType() {
        return type == ParamType.VEC2F;
    }

    public boolean requestVec3fType() {
        return type == ParamType.VEC3F;
    }

    public boolean requestQuaternionType() {
        return type  == ParamType.QUATERNION;
    }

    public boolean requestColorType() {
        return type == ParamType.COLOR;
    }

    public boolean requestPolygonType() {
        return  type == ParamType.POLYGON;
    }

    public boolean RequestAbstractClass() {
        return type == ParamType.ABSTRACT;
    }
}
