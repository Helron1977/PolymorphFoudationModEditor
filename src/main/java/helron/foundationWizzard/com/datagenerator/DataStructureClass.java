package helron.foundationWizzard.com.datagenerator;

import java.util.LinkedList;
import java.util.List;

public class DataStructureClass extends DataStructure {
    List<Parameter> parameterList;

    public DataStructureClass(String id, DataStructureType dataStructureType, LinkedList<Parameter> parameterList) {
        super(id, dataStructureType);
        this.parameterList = parameterList;
    }

    public List<Parameter> getParamLinkedList() {
        return parameterList;
    }

    public void setParamLinkedList(List<Parameter> parameterListList) {
        this.parameterList = parameterListList;
    }

}
