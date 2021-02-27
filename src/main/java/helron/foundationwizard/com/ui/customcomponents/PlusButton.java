package helron.foundationwizard.com.ui.customcomponents;

import helron.foundationwizard.com.datagenerator.ParamType;

import javax.swing.*;

public class PlusButton extends JButton {
    private final String dataStructureID;
    private ParamType requestedFormType;
    DefaultListModel<String> dataList;

    public PlusButton(String dataStructureID) {
        super("+");
        this.dataStructureID = dataStructureID;
    }

    public PlusButton(String dataStructureID, ParamType requestedFormType) {
        super("+");
        this.dataStructureID = dataStructureID;
        this.requestedFormType = requestedFormType;
    }

    public PlusButton(String wantedDataStructureid, ParamType list, DefaultListModel<String> stringList) {
        super("+");
        this.dataStructureID = wantedDataStructureid;
        this.requestedFormType = list;
        this.dataList = stringList;
    }

    public String getStructureIdRequest() {
        return dataStructureID;
    }

    public ParamType getRequestedFormType() {
        return requestedFormType;
    }

    public DefaultListModel<String> getDataList() {
        return dataList;
    }
}
