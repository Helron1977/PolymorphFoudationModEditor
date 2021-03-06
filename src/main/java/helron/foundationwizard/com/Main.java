package helron.foundationwizard.com;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import helron.foundationwizard.com.api.ApiStructuresExtractor;
import helron.foundationwizard.com.datagenerator.DataStructureMap;
import helron.foundationwizard.com.datagenerator.DataStructureMapGenerator;
import helron.foundationwizard.com.ui.Home;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class Main {
    /*
        Load the file ( SnippetsForVSC.Jsonc)
        Read as a dictionary
        look for a specific snippet template
        look for the parameter s array of the Foundation 's API
        clean the structure :
                    Get each line as a String
                    Split on '='
                    use regex to identify the vsc list position tag
        look for the VALUES of Enum Structures
     */
    public static void main(String[] args) throws IOException {



        //Load the file ( SnippetsForVSC.Jsonc)
        InputStream sourceFile = Main.class.getResourceAsStream("/struct.json");

        String configPath= "../config.properties";
        File f = new File(configPath);
        if(f.isFile())
        {
            Properties config = new Properties();
            config.load(new FileInputStream(configPath));
            System.out.println(config.getProperty("Path"));
            sourceFile = new FileInputStream(config.getProperty("Path"));
        }

        //Read
        InputStreamReader reader = new InputStreamReader(sourceFile, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);


        DataStructureMapGenerator dataStructureMapGenerator = new DataStructureMapGenerator(structures);
        DataStructureMap dataMap = dataStructureMapGenerator.getDataSet();



        new Home(dataMap);


    }
}