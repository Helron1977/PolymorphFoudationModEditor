package helron.foundationwizard.com.ui.requests;

import helron.foundationwizard.com.datagenerator.Parameter;
import helron.foundationwizard.com.ui.FormCLass;

import java.io.IOException;

    //todo modify the form to list subclasses in a abstract class
//todo create a propertie files to list subclasses of a abstract class
//todo add a Abstractclass parameter
    public class RequestAbstractClassType implements Requestable{

        @Override
        public boolean isRequired(Parameter parameter) {
            return parameter.RequestAbstractClass();
        }

        @Override
        public void action(FormCLass formCLass, Parameter parameter, int lineNumber) throws IOException {

        }
    }
