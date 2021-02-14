package helron.foundationWizzard.com.datagenerator;

public class Parameter {

    private String id;
    private ParamType type;
    private String Value;

    public Parameter(String id, ParamType paramType, String defaultValue) {
        this.id = id;
        this.Value = defaultValue;
        this.type = parseType(defaultValue);
    }

    private ParamType parseType(String defaultValue) {
        System.out.println(getValue());
        return ParamType.ASSET;
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

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
