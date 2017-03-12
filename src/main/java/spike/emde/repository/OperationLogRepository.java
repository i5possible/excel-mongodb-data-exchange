package spike.emde.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spike.emde.entity.OperationLog;

import java.util.List;

public interface OperationLogRepository extends MongoRepository<OperationLog, String>{
    List<OperationLog> findByEntityIdAndEntityType(String entityId, String EntityType);
}
