package helron.foundationwizard.com.datagenerator;

import java.util.LinkedList;
import java.util.List;

public class DataStructureClass extends DataStructure {
    private List<Parameter> parameterList;

    public DataStructureClass(String id, DataStructureType dataStructureType, LinkedList<Parameter> parameterList) {
        super(id, dataStructureType);
        this.parameterList = parameterList;
    }

    public List<Parameter> getParamList() {
        return parameterList;
    }

    public void setParamLinkedList(List<Parameter> parameterListList) {
        this.parameterList = parameterListList;
    }

}