import api.ApiStructuresExtractor;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ihm.App;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;


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
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //Load the file ( SnippetsForVSC.Jsonc)
        FileInputStream source = new FileInputStream("C:\\Users\\rolan\\OneDrive\\Desktop\\source.jsonc");

        //Read
        InputStreamReader reader = new InputStreamReader(source, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);

        LinkedHashMap<String, String> params = structures.extractClass("TEXTURE");


        App app = new App(structures);


    }
}
