package bg.borisborisov.imageviewer.service;

import bg.borisborisov.imageviewer.model.Image;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @After
    public void cleanUp() {
        imageService.deleteAll();
    }

    @Test
    public void whenSavingAnImage_thenItExists() {
        Mono<Image> image = imageService.save(
                Mono.just(new Image(UUID.randomUUID().toString(), "im1"))
        );

        assertThat(image).isNotNull();
    }
}
