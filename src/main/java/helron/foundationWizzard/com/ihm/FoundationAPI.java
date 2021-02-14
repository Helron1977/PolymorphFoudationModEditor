package helron.foundationWizzard.com.ihm;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import helron.foundationWizzard.com.Main;
import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FoundationAPI {
    ApiStructuresExtractor structures;

    public ApiStructuresExtractor getStructures() {
        return structures;
    }

    public FoundationAPI(){

        //Load the file ( SnippetsForVSC.Jsonc)
        InputStream sourceFile = Main.class.getResourceAsStream("/struct.json");

        String configPath= "../config.properties";
        File f = new File(configPath);
        if(f.isFile())
        {
            Properties config = new Properties();
            try {
                config.load(new FileInputStream(configPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(config.getProperty("Path"));
            try {
                sourceFile = new FileInputStream(config.getProperty("Path"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //Read
        InputStreamReader reader = new InputStreamReader(sourceFile, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        structures = new ApiStructuresExtractor(dictionary);

    }
}
