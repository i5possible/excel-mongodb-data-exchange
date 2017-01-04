package spike.emde.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spike.emde.card.model.Card;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card>  FindBySize(String size);
}
