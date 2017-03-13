package log.logger;

import org.springframework.beans.factory.annotation.Autowired;
import log.repository.OperationLogRepository;

public abstract class LogStrategy {

    @Autowired
    private OperationLogRepository operationLogRepository;

    abstract void doLog();

    public OperationLogRepository getOperationLogRepository() {
        return operationLogRepository;
    }

    public void setOperationLogRepository(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }
}
