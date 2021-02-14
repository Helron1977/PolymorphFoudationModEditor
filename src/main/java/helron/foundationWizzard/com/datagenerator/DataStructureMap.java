package helron.foundationWizzard.com.datagenerator;

import java.util.Map;

public class DataStructureMap {
    private Map<String, DataStructure> dataMap;


    public DataStructureMap(Map<String, DataStructure> dataMap) {
        this.dataMap= dataMap;
    }

    public DataStructureEnum getEnumData(String id){
        DataStructure dataStructure = dataMap.get(id);
        if( dataStructure.getClassType()== DataStructureType.ENUM)
            return (DataStructureEnum) dataStructure;

        throw  new IllegalArgumentException("Cette source n'est pas une Enum");
    }

    public DataStructureAsset getAssetData(String id){
        DataStructure dataStructure = dataMap.get(id);

        if( dataStructure.getClassType() == DataStructureType.ASSET)
            return (DataStructureAsset) dataStructure;

        throw new IllegalArgumentException("Cette source n'est pas une Asset");
    }

    public DataStructureClass getClassData(String id){
        DataStructure dataStructure = dataMap.get(id);

        if( dataStructure.getClassType() == DataStructureType.CLASS)
            return (DataStructureClass) dataStructure;

        throw  new IllegalArgumentException("Cette  source n'est pas une Class");
    }

    public boolean isEnum(String id){
        DataStructure dataStructure = dataMap.get(id);
        return dataStructure.getClassType().equals(DataStructureType.ENUM);
    }
    public boolean isAsset(String id){
        DataStructure dataStructure = dataMap.get(id);
        return dataStructure.getClassType().equals(DataStructureType.ASSET);
    }
    public boolean isClass(String id){
        DataStructure dataStructure = dataMap.get(id);
        return dataStructure.getClassType().equals(DataStructureType.CLASS);
    }

}
