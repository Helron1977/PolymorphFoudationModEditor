package helron.foundationWizzard.com;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import helron.foundationWizzard.com.ihm.App;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


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
    public static void main(String[] args) throws FileNotFoundException {


        //Load the file ( SnippetsForVSC.Jsonc)
        InputStream source = Main.class.getResourceAsStream("/struct.json");

        //Read
        InputStreamReader reader = new InputStreamReader(source, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);

        new App(structures);


    }
}
