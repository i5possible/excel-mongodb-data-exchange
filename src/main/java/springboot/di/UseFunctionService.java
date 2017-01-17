package springboot.di;

import org.springframework.beans.factory.annotation.Autowired;

public class UseFunctionService {
    @Autowired
    FunctionService functionService;

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public void sayHello () {
        functionService.sayHello();
    }
}
