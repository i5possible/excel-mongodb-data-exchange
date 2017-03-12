package spike.emde.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spike.emde.annotation.OperationType;
import spike.emde.entity.FieldValueChange;
import spike.emde.entity.OperationLog;
import spike.emde.entity.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class UpdateEntityLogStrategyTemplate<T> extends LogStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEntityLogStrategyTemplate.class);
    private LoggableFieldFactoryBean loggableFieldFactoryBean;
    private Object entityBeforeUpdate;
    private Object entityAfterUpdate;
    private User operator;

    public UpdateEntityLogStrategyTemplate(LoggableFieldFactoryBean loggableFieldFactoryBean,
                                           Object entityBeforeUpdate, Object entityAfterUpdate, User operator) {
        this.loggableFieldFactoryBean = loggableFieldFactoryBean;
        this.entityBeforeUpdate = entityBeforeUpdate;
        this.entityAfterUpdate = entityAfterUpdate;
        this.operator = operator;
    }

    public UpdateEntityLogStrategyTemplate(User operator, LoggableFieldFactoryBean loggableFieldFactoryBean) {
        this.operator = operator;
        this.loggableFieldFactoryBean = loggableFieldFactoryBean;
    }

    protected abstract String generateLogDescription(FieldValueChange fieldValueChange);

    protected abstract void handleEntitySpecificFieldValueChanges(
            Field loggableField, Object valueBeforeUpdate, Object valueAfterUpdate, List<FieldValueChange> changeList);

    @Override
    void doLog() {
        List<FieldValueChange> fieldValueChanges = processUpdateOperations();
        fieldValueChanges.forEach(fieldValueChange -> {
            String description = generateLogDescription(fieldValueChange);
            OperationLog log = createLog(fieldValueChange, description);
            getOperationLogRepository().save(log);
        });
    }

    private OperationLog createLog(FieldValueChange fieldValueChange, String logDescription) {
        OperationLog operationLog = new OperationLog();
        operationLog.setDescription(logDescription);
        operationLog.setOperationType(OperationType.UPDATE);
        operationLog.setFieldChange(fieldValueChange);
        operationLog.setFieldLabel(fieldValueChange.getDescriptiveName());
        operationLog.setFieldName(fieldValueChange.getFieldName());
        operationLog.setOperatorId(operator.getId());
        operationLog.setOperationName("");
        operationLog.setOperatorW3Id("");
        return operationLog;
    }

    private List<FieldValueChange> processUpdateOperations() {
        List<Field> loggableFields = loggableFieldFactoryBean.getLoggableFields(entityBeforeUpdate.getClass());
        List<FieldValueChange> fieldValueChanges = new ArrayList<>();

        loggableFields.forEach(loggableField -> {
            loggableField.setAccessible(true);
            try {
                Object beforeUpdate = loggableField.get(entityBeforeUpdate);
                Object afterUpdate = loggableField.get(entityAfterUpdate);
                FieldValueChangeFactory fieldValueChangeFactory = new FieldValueChangeFactory(loggableFieldFactoryBean,
                        (field, before, after, changes) ->
                                handleEntitySpecificFieldValueChanges(field, before, after, changes));
                fieldValueChanges.addAll(fieldValueChangeFactory.create(loggableField, beforeUpdate, afterUpdate));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    public Object getEntityBeforeUpdate() {
        return entityBeforeUpdate;
    }

    public void setEntityBeforeUpdate(Object entityBeforeUpdate) {
        this.entityBeforeUpdate = entityBeforeUpdate;
    }

    public Object getEntityAfterUpdate() {
        return entityAfterUpdate;
    }

    public void setEntityAfterUpdate(Object entityAfterUpdate) {
        this.entityAfterUpdate = entityAfterUpdate;
    }
}
