package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;

import java.io.IOException;

public interface Requestable {
    boolean isRequired(Parameter parameter);
    void action(FormCLass formCLass, Parameter parameter, int lineNumber) throws IOException;
}
