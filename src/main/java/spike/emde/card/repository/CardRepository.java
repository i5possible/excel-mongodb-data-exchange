package spike.emde.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spike.emde.card.model.Card;

public interface CardRepository extends MongoRepository<Card,String> {
}
