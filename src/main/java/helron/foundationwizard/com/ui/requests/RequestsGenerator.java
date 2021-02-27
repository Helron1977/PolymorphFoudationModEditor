package helron.foundationwizard.com.ui.requests;

import java.util.ArrayList;
import java.util.List;

public class RequestsGenerator {
List<Requestable> requestsList = new ArrayList<>();

    public RequestsGenerator() {
        requestsList.add(new RequestStringType());
        requestsList.add(new RequestEnumType());
        requestsList.add(new RequestAssetType());
        requestsList.add(new RequestBooleanType());
        requestsList.add(new RequestIntegerType());
        requestsList.add(new RequestListType());
        requestsList.add(new RequestClassType());
        requestsList.add(new RequestFloatType());
        requestsList.add(new RequestVec2iType());
        requestsList.add(new RequestVect3iType());
        requestsList.add(new RequestVec2fType());
        requestsList.add(new RequestVec3fType());
        requestsList.add(new RequestQuaternionType());
    }

    public List<Requestable> getRequestsList() {
        return requestsList;
    }
}
