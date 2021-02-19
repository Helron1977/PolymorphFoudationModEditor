package helron.foundationWizzard.com.ui.requests;

import java.util.ArrayList;
import java.util.List;

public class RequestsGenerator {
List<Requestable> requestsList = new ArrayList<>();

    public RequestsGenerator() {
        RequestStringType requestStringType = new RequestStringType();
        requestsList.add(requestStringType);
    }

    public List<Requestable> getRequestsList() {
        return requestsList;
    }
}
