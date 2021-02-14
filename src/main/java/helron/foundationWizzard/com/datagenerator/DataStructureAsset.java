package helron.foundationWizzard.com.datagenerator;

import java.util.List;

public class DataStructureAsset extends DataStructure {
    List<String> assetNames;

    public DataStructureAsset(String id, DataStructureType dataStructureType, List<String> assetNames) {
        super(id, dataStructureType);
        this.assetNames = assetNames;
    }

    public List<String> getAssetNames() {
        return assetNames;
    }

    public void setAssetNames(List<String> assetNames) {
        this.assetNames = assetNames;
    }
}
