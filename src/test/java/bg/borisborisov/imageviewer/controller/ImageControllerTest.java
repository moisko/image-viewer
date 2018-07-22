package bg.borisborisov.imageviewer.controller;

import bg.borisborisov.imageviewer.model.Image;
import bg.borisborisov.imageviewer.model.ImageEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private ImageController imageController;

    @Autowired
    private MongoOperations mongoOperations;

    // Client consuming the Reactive REST Endpoint
    private WebTestClient client;

    @Before
    public void setup() {
        client = WebTestClient.bindToController(imageController).build();
    }

    @After
    public void cleanUp() {
        mongoOperations.dropCollection(Image.class);
    }

    @Test
    public void whenGetExistingImage_thenOkResponse() {
        mongoOperations.insert(new Image("1", "im1"));

        client.get()
                .uri("/images/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void whenGetNonExistingImage_thenNotFoundResponse() {
        client.get()
                .uri("/images/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void whenGetImageEvents_thenOkStatusResponse() {
        FluxExchangeResult<ImageEvent> result = client.get()
                .uri("/images/1/events")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ImageEvent.class);
        assertThat(result).isNotNull();
        assertThat(result.getResponseBody()).isNotNull();
    }
}
