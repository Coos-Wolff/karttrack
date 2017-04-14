package nl.my.laps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "nl.my.laps")
@EntityScan(basePackages = "nl.my.laps")
public class KartTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(KartTrackApplication.class, args);
    }

}
