package helron.foundationwizard.com.api;

import helron.foundationwizard.com.datagenerator.ParamType;

public enum AbstractClasses {

    BUILDINGCONSTRUCTOR("BUILDING_CONSTRUCTOR");


    private final String shortValue;

    AbstractClasses(String shortValue) {
        this.shortValue= shortValue;
    }

    public String getShortValue() {
        return shortValue;
    }

    public static AbstractClasses searchByShortValue(String shortValue){
        for(AbstractClasses value : values()){
            if( value.shortValue.equals(shortValue)){
                return value;
            }
        }
        return null;
    }
}
