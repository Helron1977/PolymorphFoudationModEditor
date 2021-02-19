package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.Form;

public interface Requestable {
    boolean isRequired(Parameter parameter);
    void action(Form form, Parameter parameter, int lineNumber);
}
