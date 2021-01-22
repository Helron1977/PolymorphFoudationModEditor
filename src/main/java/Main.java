import api.ApiStructuresExtractor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ihm.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;


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
        FileInputStream source = new FileInputStream("C:\\Users\\rolan\\OneDrive\\Desktop\\source.jsonc");

        //Read
        InputStreamReader reader = new InputStreamReader(source, StandardCharsets.UTF_8);

        //Build dictionary and a Structures[] Index
        JsonElement dictionary = JsonParser.parseReader(reader).getAsJsonObject();
        ApiStructuresExtractor structures = new ApiStructuresExtractor(dictionary);
        System.out.println(structures.getDictionaryIndex());
        System.out.println(structures.getDictionaryAssetIndex());
        System.out.println(structures.getDictionaryClassIndex());
        System.out.println(structures.getDictionaryEnumIndex());

        structures.classToString("TEXTURE");
        structures.enumToString("TEXTURE_WRAP");
        structures.assetToString("TEXTURE");
        LinkedHashMap<String, String> params = structures.extractClass("TEXTURE");
        for (String param: params.keySet()) {
            System.out.println(params.get(param));
        }
        System.out.println(structures.extractParamDefaultValue(params.get("WrapMode")));
        System.out.println(structures.extractParamType(params.get("WrapMode")));


        //parse and Display the entire dictionary looking for API Class Template

/*        for (String index: structures.getDictionaryClassIndex()) {
            JsonArray templates = structures.extractTemplates(index);

            //Clean the templates : use regex to identify the vsc list position tag
            LinkedHashMap<String, String> parameters = ApiStructuresExtractor.clean(templates);

            //display templates s parameters name and parameters Type and default values
            ApiStructuresExtractor.consoleDisplayLinkedMapToString(parameters);
        }*/

/*        for(String index: structures.getDictionaryEnumIndex()){
            //look for the VALUES of Enum Structures
            JsonArray templates = structures.extractTemplates(index);

            //Clean the ENUM templates
            System.out.println(ApiStructuresExtractor.cleanEnumTemplate(templates));
        }*/
/*
        for(String index: structures.getDictionaryAssetIndex()){
            //look for the VALUES of Enum Structures
            JsonArray templates = structures.extractTemplates(index);

            //Clean the ENUM templates
            System.out.println(ApiStructuresExtractor.cleanEnumTemplate(templates));
        }*/

/*        Frame f = new Frame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(dim);

        for (String index : structures.getDictionaryAssetIndex()) {

            //look for the VALUES of Enum Structures
            JsonArray templates = structures.extractTemplates(index);
            JLabel lbl = new JLabel("Foundation-ENUM_TEXTURE_WRAP");
            f.getContentPane().add(lbl);

            //Clean the ENUM templates
            List<String> enumValues = ApiStructuresExtractor.cleanEnumTemplate(templates);
            JComboBox b = new JComboBox();
            for (String value: enumValues) {
                b.addItem(value);
            }
            f.getContentPane().add(b);
        }


        f.setLocation(dim.width/2 - f.getWidth()/2, dim.height/2 - f.getHeight()/2);
        f.setVisible(true);*/

    }
}
