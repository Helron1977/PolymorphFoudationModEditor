package helron.foundationWizzard.com.datagenerator;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import java.util.*;

public class DataStructureMapGenerator {
    private final DataStructureMap dataStructureMap;
    Map<String, DataStructure> extractedData;
    Map<String, DataStructure> extractedClass;
    Map<String, DataStructure> extractedEnum;
    Map<String, DataStructure> extractedAsset;

    public DataStructureMapGenerator(ApiStructuresExtractor structures) {
        LinkedList<String> dataIndex = structures.entryList();
        extractedData = new HashMap<>();
        extractedClass= new HashMap<>();
        extractedEnum = new HashMap<>();
        extractedAsset= new HashMap<>();

        generateDataSet(dataIndex, structures);
        dataStructureMap = new DataStructureMap(extractedData);
    }


    private void generateDataSet(LinkedList<String> dataIndex, ApiStructuresExtractor structures) {

        for(String index : structures.getDictionaryClassIndex())
            generateDataClass(index,structures);
        for(String index : structures.getDictionaryAssetIndex())
            generateDataAsset(index,structures);
        for(String index : structures.getDictionaryEnumIndex())
            generateDataEnum(index,structures);
    }

    private void generateDataClass(String index, ApiStructuresExtractor structures) {
        LinkedList<Parameter> parameters = generateParams(structures.extractStructure(index), structures);
        DataStructureClass data = new DataStructureClass(index, DataStructureType.CLASS, parameters);

        extractedClass.put(index, data);
        extractedData.put(index, data);
    }

    private void generateDataEnum(String index, ApiStructuresExtractor structures) {
        DataStructureEnum data = new DataStructureEnum(index, DataStructureType.ENUM, structures.assetToList(index));
        extractedEnum.put(index, data);
        extractedData.put(index, data);
    }

    private void generateDataAsset (String index, ApiStructuresExtractor structures){
        DataStructureAsset data = new DataStructureAsset(index, DataStructureType.ASSET, structures.assetToList(index));
        extractedAsset.put(index, data);
        extractedData.put(index, data);
    }

    private LinkedList<Parameter> generateParams(LinkedHashMap<String, String> extractedData, ApiStructuresExtractor structures){
        LinkedList<Parameter> parameters = new LinkedList<>();
        extractedData.forEach((key, value) ->{
                //System.out.println(structures.extractParamType(value));
            ParamType paramType = checkParamType(structures.extractParamType(value));
            String defaultValue = structures.extractParamDefaultValue(value);
                //System.out.println(structures.extractParamDefaultValue(value));
            Parameter parameter =new Parameter(key,paramType, defaultValue);

            parameters.add(parameter);

        });

        return parameters;
    }

    private ParamType checkParamType(String extractParamType) {
        if (extractParamType.contains("list"))
            return ParamType.LIST;
        else if (extractParamType.contains("PAIR"))
            return ParamType.PAIR;

        return switch (extractParamType) {
            case "float" -> ParamType.FLOAT;
            case "boolean" -> ParamType.BOOLEAN;
            case "string" -> ParamType.STRING;
            case "integer" -> ParamType.INTEGER;
            case "vecf2f" -> ParamType.VEC2F;
            case "vec3f" -> ParamType.VEC3F;
            case "vec2i" -> ParamType.VEC2I;
            default -> null;
        };
    }

    public DataStructureMap getDataSet() {
        return dataStructureMap;
    }
}

