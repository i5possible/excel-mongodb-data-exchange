package spike.emde.model;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToExportField {
    String exportName() default "";
    int exportIndex();
}
