package helron.foundationWizzard.com.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * Generate a file.lua, script of Asset declaration using Foundation API of a Polymorph Game.
 * Use  LinkedHashMap, param / Jcomponent as param to write the lua file with the correct syntax
 * Each Jcomponent has it own method toLUA()
 * Each file has a prefix declaration
 * Each file can have a link to another lua file.
 */
public class LuaGenerator {
    private static LinkedHashMap<String, String> userInput = new LinkedHashMap<>();

    public LuaGenerator(LinkedHashMap<String, String> userInput) {
        LuaGenerator.userInput = userInput;

    }

    public LuaGenerator() {
    }

    /**
     * Build a String, a lua Table script.
     * @param localVar Name of the lua Table
     * @return a String, the script.
     */
    public String InitializeLuaTable(String localVar) {
        StringBuilder sb = new StringBuilder();
        String body = buildLuaTable();

        sb.append(localVar)
                .append(":register(")
                .append(body)
                .append(")");
        return sb.toString();
    }

    /**
     * Build a table lua script representation of an Asset in Foundation
     * @return a string, the script.
     */
    public String buildLuaTable() {
        StringJoiner sj = new StringJoiner(",\n", "{\n","\n}");

        for (String param : userInput.keySet()) {
            String scriptLine = param + " = " + userInput.get(param);
            sj.add(scriptLine);
        }
        return sj.toString();
    }

    /**
     * Build a List lua script representation of a ArrayList of Asset ID in Foundation
     * @param listToScript a list that contain the Asset names.
     * @return a string, the script
     */
    public String buildLuaList(List<String> listToScript) {
        StringJoiner sj = new StringJoiner(",\n\t","{\n\t","\n}");
        for (String value: listToScript) {
            sj.add(value);
        }
        return sj.toString();
    }

    //test
    public static void main(String[] args) {
        LinkedHashMap<String, String> test = new LinkedHashMap<>();
        List<String> listtotest = new ArrayList<>();
        String param = "param";
        String field = "contenu de field";
        for(int i=0; i <10; i++){
            test.put(param+i,field+i);
            listtotest.add("list"+i);
        }
        LuaGenerator lg = new LuaGenerator(test);
        String aLuaList = lg.buildLuaList(listtotest);
        test.put("paramlast", aLuaList);
        LuaGenerator lg2 = new LuaGenerator(test);
        System.out.println(lg.buildLuaTable());
    }
}
