import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.util.*;


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
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Load the file ( SnippetsForVSC.Jsonc)
        FileInputStream source = new FileInputStream("C:\\Users\\rolan\\OneDrive\\Desktop\\source.jsonc");

        //Read
        InputStreamReader reader = new InputStreamReader(source, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);
        //System.out.println(structures.entryList().toString());

        //parse and Display the entire dictionary
        for(int i = 0; i < structures.entryList().size(); i++) {
            //look for a specific snippet template
            JsonArray structure = structures.extractTemplate(structures.entryList().get(i));


            //Clean the structure : use regex to identify the vsc list position tag
            LinkedHashMap<String, String> parameters = ApiStructuresExtractor.clean(structure);

            //display structure parameters name and parameters Type and default values
            ApiStructuresExtractor.consoleDisplaylinkedMapToString(parameters);
        }


    }

}
