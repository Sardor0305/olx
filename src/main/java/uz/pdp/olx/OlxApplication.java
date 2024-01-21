package uz.pdp.olx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OlxApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlxApplication.class, args);
    }

}
