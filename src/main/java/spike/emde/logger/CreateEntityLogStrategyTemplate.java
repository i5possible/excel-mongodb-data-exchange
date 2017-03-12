package spike.emde.logger;

import spike.emde.annotation.OperationType;
import spike.emde.entity.OperationLog;

public abstract class CreateEntityLogStrategyTemplate<T> extends LogStrategy {
    @Override
    void doLog() {
        String description = generateLogDescription();
        OperationLog log = createLog(description);
        getOperationLogRepository().save(log);
    }

    OperationLog createLog(String description) {
        OperationLog operationLog = new OperationLog();
        operationLog.setDescription(description);
        operationLog.setOperationType(OperationType.CREATE);
        return operationLog;
    }

    protected abstract String generateLogDescription();
}
