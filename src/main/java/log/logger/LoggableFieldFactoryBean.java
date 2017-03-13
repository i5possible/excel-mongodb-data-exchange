package log.logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import log.annotation.LoggableEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggableFieldFactoryBean implements InitializingBean{
    @Autowired
    private ApplicationContext applicationContext;

    private static final Map<Class, List<Field>> LOGGABLE_ENTITY_FIELD_MAPPING = new HashMap<>();
    private static final Map<Class, Field> LOG_NAME_FIELD_MAPPING = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> loggableEntities = applicationContext.getBeansWithAnnotation(LoggableEntity.class);
    }

    public List<Field> getLoggableFields(Class type) {
        return LOGGABLE_ENTITY_FIELD_MAPPING.get(type);
    }

    public Field getLogNameField(Class type) {
        return LOG_NAME_FIELD_MAPPING.get(type);
    }
}
