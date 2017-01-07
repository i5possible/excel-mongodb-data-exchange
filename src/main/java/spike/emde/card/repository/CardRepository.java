package spike.emde.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spike.emde.card.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findBySize(String size);
    Optional<Card> findById(String id);
}
