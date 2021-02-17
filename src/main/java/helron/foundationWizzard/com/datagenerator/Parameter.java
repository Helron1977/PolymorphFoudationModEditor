package helron.foundationWizzard.com.datagenerator;

public class Parameter {

    private String id;
    private ParamType type;
    private String value;

    public Parameter(String id, ParamType paramType, String defaultValue) {
        this.id = id;
        this.value = defaultValue;
        this.type = paramType;
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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
