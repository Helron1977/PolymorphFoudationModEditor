package helron.foundationWizzard.com.datagenerator;

public enum ParamType {
    //primitiveType
    FLOAT("float"),
    BOOLEAN("boolean"),
    STRING("string"),
    INTEGER("integer"),
    //ListType
    LIST("list"),
    //dataStructureType,
    ASSET(""),
    CLASS(""),
    ENUM(""),
    //dataStructureStruct, same treatment as Class,
    LINE("LINE"),
    PHYSICS_RAY_RESULT("PHYSICS_RAY_RESULT"),
    COLOR("color"),
    MATRIX("matrix"),
    POLYGON("polygon"),
    QUATERNION("quaternion"),
    VEC2D("vec2d"),
    VEC2F("vec2f"),
    VEC2I("vec2i"),
    VEC3D("vec3d"),
    VEC3F("vec3f"),
    VEC3I("vec3i");

private final String shortValue;

    ParamType(String shortValue) {
        this.shortValue= shortValue;
    }

    public String getShortValue() {
        return shortValue;
    }

    public static ParamType searchByShortValue(String shortValue){
        for(ParamType value : values()){
            if( value.shortValue.equals(shortValue)){
                return value;
            }
        }
        return null;
    }

}
