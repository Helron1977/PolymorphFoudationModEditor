package helron.foundationWizzard.com.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import helron.foundationWizzard.com.datagenerator.DataStructure;
import helron.foundationWizzard.com.datagenerator.DataStructureType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiStructuresExtractor {
    private final static Pattern custom = Pattern.compile("([A-Z])\\w+");
    private final static Pattern defaultValueMatcher = Pattern.compile("\\S+(?=\\))");
    private final static Pattern dataIDExtractorFromParam = Pattern.compile("\\<(.*?)\\>");
    private final String CLASS_PREFIX = DataStructureType.CLASS.getPrefix();

    private final String ENUM_PREFIX = DataStructureType.ENUM.getPrefix();

    private final String ASSET_PREFIX = DataStructureType.ASSET.getPrefix();
    private final String STRUCTURE_PREFIX = DataStructureType.STRUCTURE.getPrefix();
    private final JsonElement dictionary;
    private final LinkedList<String> dictionaryIndex;
    private final LinkedList<String> dictionaryClassIndex;
    private final LinkedList<String> dictionaryEnumIndex;
    private final LinkedList<String> dictionaryAssetIndex;
    private final LinkedList<String> dictionaryStructIndex;
    /**
     *Set a JsonElement as a dictionary and set an Index list , an Index Class list, an Index Enum list, an Asset list
     * @param dictionary A JsonElement result of a JsonParser.parseReader( Reader) to JsonObject
     */
    public ApiStructuresExtractor(JsonElement dictionary) {
        this.dictionary = dictionary;
        this.dictionaryIndex = entryList();
        this.dictionaryClassIndex = extractMatchTypeIndex(dictionaryIndex, DataStructureType.CLASS.getPrefix());
        this.dictionaryAssetIndex = extractMatchTypeIndex(dictionaryIndex, DataStructureType.ASSET.getPrefix());
        this.dictionaryEnumIndex = extractMatchTypeIndex(dictionaryIndex, DataStructureType.ENUM.getPrefix());
        this.dictionaryStructIndex = extractMatchTypeIndex(dictionaryIndex,DataStructureType.STRUCTURE.getPrefix());
    }

    public String extractWantedDataStructureId(String paramDescription) {
        Matcher m = dataIDExtractorFromParam.matcher(paramDescription);

        while (m.find()) {
            paramDescription = m.group();
        }
        return paramDescription.replace("<","").replace(">","");
    }

    /**
     * Extract and Store in a LinkedList String all the Index matching with the String use in arg
     * @param dictionaryIndex LinkedList String Index of the Json API descriptor
     * @param matchingString String to find in Index LinkedList String
     * @return LinkedList String of an Index Type
     */
    public LinkedList<String> extractMatchTypeIndex(LinkedList<String> dictionaryIndex, String matchingString) {
        LinkedList<String> dictionaryMatchTypeIndex = new LinkedList<>();

        for (String index: dictionaryIndex) {
            if(index.contains(matchingString))
                dictionaryMatchTypeIndex.add(index);
        }
        return dictionaryMatchTypeIndex;
    }

    /**
     * Clean a jsonArray of VSC snippet's tags and DataType param.
     * @param structure JsonArray of parameter Set
     * @return LinkedHasMap (Parameter Label ; String Parameter Type and default Value)
     */
    public static LinkedHashMap<String, String> clean(JsonArray structure) {
        String line;
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>() {
        };

        for (JsonElement key: structure) {
            line = key.getAsString();
            String[] parameter = line.split("=");

            if (parameter.length == 2) {
                parameter[0] = parameter[0].replaceAll("\\s", "");
                parameter[1] = parameter[1].replace(" ${", "")
                        .replace("},", "")
                        .replace("\",", "\"")
                        .replace("\"", "")
                        .replaceAll("[\\d]+:", "");
                //if(!parameter[0].equals("DataType"))
                    parameters.put(parameter[0], parameter[1]);
            }
        }
        return parameters;
    }

    /**
     * Display in Console, line per line, each parameter and parmeter type contained in this map of parameters.
     * @param parameters a LinkedHashMap String, String of structure s parameters.
     */
    public static void consoleDisplayLinkedMapToString(LinkedHashMap<String, String> parameters) {
        for (String param : parameters.keySet()) {
            System.out.print(param + " = ");
            System.out.println(parameters.get(param));
        }
    }

    /**
     * return true if the String use in param match with an Enum of the API
     * @param paramType the String you are looking for.
     * @return a boolean true/false
     */
    public boolean isEnum(String paramType){
        for(String index: getDictionaryEnumIndex()) {
            if((ENUM_PREFIX+paramType).equals(index)){
                //System.out.println("enum");
                return true;}
        }
        return false;
    }

    /**
     * return true if the String use in param match with an Asset of the API
     * @param paramType the String you are looking for.
     * @return a boolean true/false
     */
    public boolean isAsset(String paramType){
        for(String index: getDictionaryAssetIndex()) {
            if((ASSET_PREFIX+paramType).equals(index)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if the String use in param match with an Class of the API
     * @param paramType the String you are looking for.
     * @return a boolean true/false
     */
    public boolean isClass(String paramType){
        for(String index: getDictionaryClassIndex()){
            if((CLASS_PREFIX+paramType).equals(index)) {
                return true;
            }
        }
        return false;
    }
    /**
     * return true if the String use in param match with a Struct of the API
     * @param paramType the String you are looking for.
     * @return a boolean true/false
     */
    public boolean isStruct(String paramType){
        for(String index: getDictionaryAssetIndex()) {
            if((ASSET_PREFIX+paramType).equals(index)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Parse the Capital letter as VALUES of Enums. Enums do not have an equal char.So can be identify by a lenght of 1
     * @param templates JsonArray of JsonElement containing a code to parse
     * @return linkedList of String, the Enums Values
     */
    public static List<String> cleanEnumTemplate(JsonArray templates) {
        LinkedList<String> enumValues = new LinkedList<>();

        for( JsonElement template : templates){
            String line = template.getAsString();
            Matcher m = custom.matcher(line);

            while (m.find()) {
                enumValues.add(m.group());
            }
        }
        return enumValues;
    }

    /**
     * Provide a List Strings of structures NAMES corresponding to Class or Enum describe in the Json VSC Snippets file.
     * Here the code compare this list to the Entry summary of a dictionary.
     * @return a List Strings of the entry contained in the JsonElement dictionary
     */
    public LinkedList<String> entryList(){
        LinkedList<String> entry = new LinkedList<>();
        Set<Map.Entry<String, JsonElement>> structuresIndex = this.dictionary.getAsJsonObject().entrySet();
        for (Map.Entry<String, JsonElement> test: structuresIndex) {
                entry.add(test.getKey());
        }
        return entry;
    }

    /**
     * Extract a specific API Template contained in the Json VSC Snippets file.
     * Here the code compare this template as a definition in a dictionary
     * @param templateToExtract String template to extract
     * @return JsonArray extracted Template
     */
    public JsonArray extractTemplates(String templateToExtract){
        JsonObject vscTemplate = dictionary.getAsJsonObject().getAsJsonObject(templateToExtract);
        return vscTemplate.getAsJsonArray("body");
    }

    /**
     * Extract the parameters of a API Class using a String ID without the prefix set as CONST.
     * @param classToExtract a String Name, id of the class to display
     */
    public void classToString(String classToExtract){
        JsonArray templates = extractTemplates(CLASS_PREFIX+ classToExtract);
        LinkedHashMap<String, String> parameters = ApiStructuresExtractor.clean(templates);
        for (String param : parameters.keySet()) {
            System.out.print(param + " = ");
            System.out.println(parameters.get(param));
        }
    }

    /**
     * Extract the default Value from a String param Type that contain it.
     * ex: TEXTURE_WRAP (default: TEXTURE_WRAP.CLAMP) returns TEXTURE_WRAP.CLAMP
     * @param param String Param containing the default value.
     * @return String the default value.
     */
    public String extractParamDefaultValue(String param){
        Matcher m = defaultValueMatcher.matcher(param);
        if(m.find()){
            //System.out.println(m.group());
            return m.group();}
        return null;
    }

    /**
     * Extract an expected parameter type from String param, first word of the String.
     * @param param String Param containing the expected Type.
     * @return a String, expected parameter type.
     */
    public String extractParamType(String param){
        Scanner sc = new Scanner(param);
        return sc.next();
    }

    /**
     * Extract a Class of the Api using a String ID whitout the prefix set as CONST.
     * @param classToExtract a String id of the Class to extract
     * @return a LinkedHashMap param, param type
     */
    public LinkedHashMap<String, String> extractClass(String classToExtract) {
        JsonArray templates = extractTemplates(CLASS_PREFIX+ classToExtract);
        return ApiStructuresExtractor.clean(templates);
    }

    public LinkedHashMap<String, String> extractStructure(String classToExtract) {
        JsonArray templates = extractTemplates(classToExtract);
        return ApiStructuresExtractor.clean(templates);
    }
    /**
     * Extract the values of an API ENUM
     * @param enumToExtract a String, id of the Enum to display
     * @return A List String of the Enum values
     */
    public List<String> EnumToList(String enumToExtract){
        //look for the VALUES of Enum Structures
        JsonArray templates = extractTemplates(ENUM_PREFIX+enumToExtract);
        //Clean the ENUM templates
        return cleanEnumTemplate(templates);
        //System.out.println(ApiStructuresExtractor.cleanEnumTemplate(templates));
    }

    /**
     * Extract the values of the API
     * @param assetToExtract a String, id of the Asset to display
     * @return a List of String
     */
    public List<String> assetToList(String assetToExtract) {
        //look for the VALUES of Enum Structures
        JsonArray templates = extractTemplates(assetToExtract);

        //Clean the ENUM templates
        return ApiStructuresExtractor.cleanEnumTemplate(templates);
    }

    public String extractDataStructureEnumID(String paramDescription){
        return new Scanner(paramDescription).next();
    }
    public String extractDataStructureAssetID(String paramDescription) {
        return new Scanner(paramDescription).next();
    }

    public List<String> getDictionaryIndex() {
        return dictionaryIndex;
    }

    public LinkedList<String> getDictionaryClassIndex() {
        return dictionaryClassIndex;
    }

    public LinkedList<String> getDictionaryEnumIndex() {
        return dictionaryEnumIndex;
    }

    public LinkedList<String> getDictionaryAssetIndex() {
        return dictionaryAssetIndex;
    }

    public LinkedList<String> getDictionaryStructIndex() {
        return dictionaryStructIndex;
    }

    public String getCLASS_PREFIX() {
        return CLASS_PREFIX;
    }

    public String getENUM_PREFIX() {
        return ENUM_PREFIX;
    }

    public String getASSET_PREFIX() {
        return ASSET_PREFIX;
    }
}
