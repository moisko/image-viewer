package bg.borisborisov.imageviewer.repository;

import bg.borisborisov.imageviewer.model.Image;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends ReactiveMongoRepository<Image, String> {
}
