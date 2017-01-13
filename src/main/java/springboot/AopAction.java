package springboot;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopAction {
    @AliasFor("name")
    String value() default  "";

    @AliasFor("value")
    String name() default  "";
}
