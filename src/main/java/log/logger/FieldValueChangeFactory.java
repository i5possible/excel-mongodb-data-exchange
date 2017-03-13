package log.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import log.entity.FieldValueChange;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldValueChangeFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEntityLogStrategyTemplate.class);
    private LoggableFieldFactoryBean loggableFieldFactoryBean;
    private FieldValueChangeHandler fieldValueChangeHandler;

    public FieldValueChangeFactory(LoggableFieldFactoryBean loggableFieldFactoryBean, FieldValueChangeHandler fieldValueChangeHandler) {
        this.loggableFieldFactoryBean = loggableFieldFactoryBean;
        this.fieldValueChangeHandler = fieldValueChangeHandler;
    }
    
    public List<FieldValueChange> create(Field loggableField, Object valueBeforeUpdate, Object valueAfterUpdate) {
        List<FieldValueChange> fieldValueChanges = new ArrayList<>();
        Class type = loggableField.getType();
        // TODO: 07/03/2017  
        return fieldValueChanges;
    }
    
    private boolean isEquivalent(Object valueBeforeUpdate, Object valueAfterUpdate) {
        return valueBeforeUpdate == null && valueAfterUpdate == null
                || valueBeforeUpdate != null && valueBeforeUpdate.equals(valueAfterUpdate);
    }
    
    private Object getLogNameFieldValue(Object valueBeforeUpdate, Class fieldType, Field logNameField) {
        // TODO: 07/03/2017  
        return null;
    }
}
