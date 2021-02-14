package helron.foundationWizzard.com.datagenerator;

import java.util.List;

public class DataStructureEnum extends DataStructure {
    List<String> enumValues;

    public DataStructureEnum(String id, DataStructureType dataStructureType, List<String> enumValues) {
        super(id, dataStructureType);
        this.enumValues = enumValues;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues;
    }
}
