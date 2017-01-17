package springboot.aop;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodService {
    @AopAction
    public void add(){}
}
