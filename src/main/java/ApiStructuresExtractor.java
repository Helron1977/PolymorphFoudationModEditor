import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiStructuresExtractor {
    private final JsonElement dictionary;
    private final static Pattern custom = Pattern.compile("([A-Z])\\w+");

    /**
     *Set a JsonElement as a dictionary
     * @param dictionary A JsonElement result of a JsonParser.parseReader( Reader) to JsonObject
     */
    public ApiStructuresExtractor(JsonElement dictionary) {
        this.dictionary = dictionary;
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
    public static void consoleDisplaylinkedMapToString(LinkedHashMap<String, String> parameters) {
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
            String[] enumTemplate = line.split("=");
            Matcher m = custom.matcher(enumTemplate[0]);

            if (enumTemplate.length == 1 && m.find()) {
                while (m.find()) {
                    enumValues.add(m.group());
                    //System.out.print(m.group() + " ");
                }
                System.out.println();
            }
        }
        return enumValues;
    }

    /**
     * Provide a List Strings of structures NAMES corresponding to Class or Enum describe in the Json VSC Snippets file.
     * Here the code compare this list to the Entry summary of a dictionary.
     * @return a List Strings of the entry contained in the JsonElement dictionary
     */
    public List<String> entryList(){
        List<String> entry = new ArrayList<>();
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
}