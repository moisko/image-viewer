package bg.borisborisov.imageviewer;

import bg.borisborisov.imageviewer.model.Image;
import bg.borisborisov.imageviewer.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class ImageViewerApplication {

    @Bean
    CommandLineRunner importData(ImageRepository imageRepository) {

        return args ->
                imageRepository.deleteAll().thenMany(
                        imageRepository.saveAll(Arrays.asList(
                                new Image(UUID.randomUUID().toString(), "im1"),
                                new Image(UUID.randomUUID().toString(), "im2"),
                                new Image(UUID.randomUUID().toString(), "im3"),
                                new Image(UUID.randomUUID().toString(), "im4")
                        ))
                );
    }

    public static void main(String[] args) {
        SpringApplication.run(ImageViewerApplication.class, args);
    }
}
