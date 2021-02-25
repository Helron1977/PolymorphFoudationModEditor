package helron.foundationWizzard.com.ui.requests;

import helron.foundationWizzard.com.datagenerator.Parameter;
import helron.foundationWizzard.com.ui.FormCLass;

public interface Requestable {
    boolean isRequired(Parameter parameter);
    void action(FormCLass formCLass, Parameter parameter, int lineNumber);
}
