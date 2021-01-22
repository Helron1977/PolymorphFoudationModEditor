package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiStructuresExtractor {
    private final static Pattern custom = Pattern.compile("([A-Z])\\w+");
    private final String CLASSPREFIX = "Foundation-CLASS_";
    private final String ENUMPREFIX = "Foundation-ENUM_";
    private static final String ASSETPREFIX = "Foundation-ASSET_";
    private final JsonElement dictionary;
    private final LinkedList<String> dictionaryIndex;

    private final LinkedList<String> dictionaryClassIndex;
    private final LinkedList<String> dictionaryEnumIndex;
    private final LinkedList<String> dictionaryAssetIndex;

    /**
     *Set a JsonElement as a dictionary and set an Index list , an Index Class list, an Index Enum list, an Asset list
     * @param dictionary A JsonElement result of a JsonParser.parseReader( Reader) to JsonObject
     */
    public ApiStructuresExtractor(JsonElement dictionary) {
        this.dictionary = dictionary;
        this.dictionaryIndex = entryList();
        this.dictionaryClassIndex = extractMatchTypeIndex(dictionaryIndex, "CLASS");
        this.dictionaryAssetIndex = extractMatchTypeIndex(dictionaryIndex, "-ASSET");
        this.dictionaryEnumIndex = extractMatchTypeIndex(dictionaryIndex, "ENUM");
    }

    /**
     * Extract and Store in a LinkedList String all the Index matching with the String use in arg
     * @param dictionaryIndex LinkedList String Index of the Json API descriptor
     * @param matchingString String to find in Index LinkedList String
     * @return LinkedList String of an Index Type
     */
    private LinkedList<String> extractMatchTypeIndex(LinkedList<String> dictionaryIndex, String matchingString) {
        LinkedList<String> dictionaryMatchTypeIndex = new LinkedList<>();

        for (String index: dictionaryIndex) {
            if(index.contains(matchingString))
                dictionaryMatchTypeIndex.add(index);
        }
        return dictionaryMatchTypeIndex;
    }

    /**
     * Clean a jsonArray of VSC snippet's tags
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
            if(parameter.length == 2) {
                parameter[0] = parameter[0].replaceAll("\\s","");
                parameter[1]= parameter[1].replace(" ${","")
                        .replace("},","")
                        .replace("\",","\"")
                        .replace("\"","")
                        .replaceAll("[\\d]+:", "");
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
            //System.out.println(test.getKey());
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
        JsonArray templates = extractTemplates(CLASSPREFIX+ classToExtract);
        LinkedHashMap<String, String> parameters = ApiStructuresExtractor.clean(templates);
        for (String param : parameters.keySet()) {
            System.out.print(param + " = ");
            System.out.println(parameters.get(param));
        }
    }

    /**
     * Extract a Class of the Api using a String ID whitout the prefix set as CONST.
     * @param classToExtract a String id of the Class to extract
     * @return a LinkedHashMap param, param type
     */
    public LinkedHashMap<String, String> extractClass(String classToExtract) {
        JsonArray templates = extractTemplates(CLASSPREFIX+ classToExtract);
        return ApiStructuresExtractor.clean(templates);
    }

    /**
     * Extract the values of an API ENUM using an id without the prefix, set as CONST.
     * @param enumToExtract a String, id of the Enum to display
     */
    public void enumToString(String enumToExtract){
        //look for the VALUES of Enum Structures
        JsonArray templates = extractTemplates(ENUMPREFIX+enumToExtract);
        //Clean the ENUM templates
        System.out.println(ApiStructuresExtractor.cleanEnumTemplate(templates));
    }

    /**
     * Extract the values of the API ASSET using a String ID without the prefix set as CONST.
     * @param assetToExtract a String, id of the Asset to display
     */
    public void assetToString(String assetToExtract) {
        //look for the VALUES of Enum Structures
        JsonArray templates = extractTemplates(ASSETPREFIX+assetToExtract);

        //Clean the ENUM templates
        System.out.println(ApiStructuresExtractor.cleanEnumTemplate(templates));
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
}