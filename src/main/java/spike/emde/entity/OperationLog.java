package spike.emde.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import spike.emde.annotation.OperationType;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "operationLogs")
@CompoundIndex(name = "entityId_1_entityType_1", def = "{'entityId' : 1, 'entityType' : 1}", unique = false)
public class OperationLog {
    @Id
    private String id;
    @NotNull
    private Date createAt;
    @NotEmpty
    private String operatorId;
    @NotEmpty
    private String operationName;
    @NotNull
    private OperationType operationType;
    @NotEmpty
    private String entityId;
    @NotEmpty
    private String entityType;
    private FieldValueChange fieldChange;
    @NotEmpty
    private String description;
    @NotEmpty
    private String operatorW3Id;
    private String fieldName;
    private String fieldLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public FieldValueChange getFieldChange() {
        return fieldChange;
    }

    public void setFieldChange(FieldValueChange fieldChange) {
        this.fieldChange = fieldChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperatorW3Id() {
        return operatorW3Id;
    }

    public void setOperatorW3Id(String operatorW3Id) {
        this.operatorW3Id = operatorW3Id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }
}
