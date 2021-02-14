/*
package helron.foundationWizzard.com.ihm;

import helron.foundationWizzard.com.api.ApiStructuresExtractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class newForm extends JPanel {
    private String classID;

    public newForm(String classID) {
        this.classID = classID;

        FoundationAPI foundationAPI = new FoundationAPI();
        ApiStructuresExtractor apiStructures = foundationAPI.getStructures();
        LinkedHashMap<String, String> params = apiStructures.extractClass(classID);

        int cptRow = 0;
        for( String param : params.keySet()){
            String fullParamDescription = params.get(param);
            String paramType = apiStructures.extractParamType(fullParamDescription);
            String defaultValue = apiStructures.extractParamDefaultValue(fullParamDescription);
            FormField formField = new FormField();


            FormRow row = new FormRow(param, new JTextField(defaultValue), cptRow );


            this.add(row);
            cptRow++;
        }
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.add(new newForm("BUILDING"));
        frame.setVisible(true);
    }
}
*/
