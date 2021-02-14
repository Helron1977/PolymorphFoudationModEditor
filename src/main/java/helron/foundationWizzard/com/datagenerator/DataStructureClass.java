package helron.foundationWizzard.com.datagenerator;

import java.util.LinkedList;

public class DataStructureClass extends DataStructure {
    LinkedList<Parameter> parameterLinkedList;

    public DataStructureClass(String id, DataStructureType dataStructureType, LinkedList<Parameter> parameterLinkedList) {
        super(id, dataStructureType);
        this.parameterLinkedList = parameterLinkedList;
    }

    public LinkedList<Parameter> getParamLinkedList() {
        return parameterLinkedList;
    }

    public void setParamLinkedList(LinkedList<Parameter> parameterLinkedList) {
        this.parameterLinkedList = parameterLinkedList;
    }

}
