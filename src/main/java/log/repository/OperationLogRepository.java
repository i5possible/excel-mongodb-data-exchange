package log.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import log.entity.OperationLog;

import java.util.List;

public interface OperationLogRepository extends MongoRepository<OperationLog, String>{
    List<OperationLog> findByEntityIdAndEntityType(String entityId, String EntityType);
}
