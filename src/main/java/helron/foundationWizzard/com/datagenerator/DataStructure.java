package helron.foundationWizzard.com.datagenerator;

public class DataStructure {
    private String id;
    private DataStructureType dataStructureType;

    public DataStructure(String id, DataStructureType dataStructureType) {
        this.id = id;
        this.dataStructureType = dataStructureType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public DataStructureType getDataStructureType() {
        return dataStructureType;
    }

    public void setDataType(DataStructureType dataStructureType) {
        this.dataStructureType = dataStructureType;
    }


}
