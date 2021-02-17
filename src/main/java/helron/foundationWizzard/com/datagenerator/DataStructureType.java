package helron.foundationWizzard.com.datagenerator;

public enum DataStructureType {
    ENGINECORE(null),
    ASSET("Foundation-ASSET_"),
    ASSET_PROCESSOR(null),
    CLASS("Foundation-CLASS_"),
    STRUCTURE("Foundation-STRUCT_"),
    ENUM("Foundation-ENUM_");

    private final String prefix;

    public String getPrefix() {
        return prefix;
    }

    DataStructureType(String prefix) {
        this.prefix =prefix;
    }

}
