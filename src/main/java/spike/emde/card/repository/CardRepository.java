package spike.emde.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spike.emde.card.model.CardInfo;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends MongoRepository<CardInfo, String> {
    List<CardInfo> findBySize(String size);

    Optional<CardInfo> findById(String id);
}
