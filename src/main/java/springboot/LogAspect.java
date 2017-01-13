package springboot;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(springboot.AopAction)")
    public void annotationPointCut() {
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AopAction annotation = method.getAnnotation(AopAction.class);
        System.out.println("Annotation intercept :"+annotation.name());
    }

    @Before("execution(* springboot.DemoMethodService.add(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AopAction annotation = method.getAnnotation(AopAction.class);
        System.out.println("Method intercept"+annotation.name());
    }
}
