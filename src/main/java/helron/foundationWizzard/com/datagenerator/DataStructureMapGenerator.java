package helron.foundationWizzard.com.datagenerator;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import java.util.*;

public class DataStructureMapGenerator {
    private final ApiStructuresExtractor structures;
    private final DataStructureMap dataStructureMap;
    Map<String, DataStructure> extractedData;
    Map<String, DataStructure> extractedClass;
    Map<String, DataStructure> extractedEnum;
    Map<String, DataStructure> extractedAsset;
    Map<String, DataStructure> extractedStruct;


    public DataStructureMapGenerator(ApiStructuresExtractor structures) {
        this.structures=structures;

        LinkedList<String> dataIndex = structures.entryList();
        extractedData = new HashMap<>();
        extractedClass= new HashMap<>();
        extractedEnum = new HashMap<>();
        extractedAsset= new HashMap<>();
        extractedStruct= new HashMap<>();

        generateDataSet(dataIndex);
        dataStructureMap = new DataStructureMap(extractedData);
    }


    private void generateDataSet(LinkedList<String> dataIndex) {

        for(String index : structures.getDictionaryClassIndex())
            generateDataClass(index);
        for(String index : structures.getDictionaryAssetIndex())
            generateDataAsset(index);
        for(String index : structures.getDictionaryEnumIndex())
            generateDataEnum(index);
        for(String index : structures.getDictionaryStructIndex())
            generateDataClass(index);
    }


    private void generateDataClass(String index) {
        LinkedList<Parameter> parameters = generateParams(structures.extractStructure(index));
        DataStructureClass data = new DataStructureClass(index, DataStructureType.CLASS, parameters);

        extractedClass.put(index, data);
        extractedData.put(index, data);
    }

    private void generateDataEnum(String index) {
        DataStructureEnum data = new DataStructureEnum(index, DataStructureType.ENUM, structures.assetToList(index));
        extractedEnum.put(index, data);
        extractedData.put(index, data);
    }

    private void generateDataAsset (String index){
        DataStructureAsset data = new DataStructureAsset(index, DataStructureType.ASSET, structures.assetToList(index));
        extractedAsset.put(index, data);
        extractedData.put(index, data);
    }

    private LinkedList<Parameter> generateParams(LinkedHashMap<String, String> extractedData){
        LinkedList<Parameter> parameters = new LinkedList<>();
        extractedData.forEach((key, value) ->{
            Parameter parameter =new Parameter(key);

            ParamType paramType = checkParamType(structures.extractParamType(value));
            //System.out.println(structures.extractParamType(value)+" "+value+" "+paramType);
            String defaultValue = structures.extractParamDefaultValue(value);
            parameter.setDescription(value);
            parameter.setType(paramType);
            parameter.setDefaultValue(defaultValue);

            if (parameter.requestEnumType()){
                String enumId = structures.extractDataStructureEnumID(value);
                parameter.setValues(structures.EnumToList(enumId));
            }
            if (parameter.requestAssetType()){
                List<String> assetList = structures.assetToList(structures.getASSET_PREFIX()+new Scanner(parameter.getDescription()).next());
                parameter.setValues(assetList);

            }






            parameters.add(parameter);

        });

        return parameters;
    }

    private ParamType checkParamType(String extractParamType) {

        if(structures.isAsset(extractParamType)){
            return ParamType.ASSET;
        } else if (structures.isEnum(extractParamType)){
            return ParamType.ENUM;
        } else if (structures.isClass(extractParamType)){
            return ParamType.CLASS;
        } else if( extractParamType.contains("list<")){
            return ParamType.LIST;
        } else {
            return ParamType.searchByShortValue(extractParamType);
        }
    }

    public DataStructureMap getDataSet() {
        return dataStructureMap;
    }
}

