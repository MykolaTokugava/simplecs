package ua.metlife.claims.simplecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("ua.metlife.claims.simplecs.repo")
@EntityScan(basePackages = "ua.metlife.claims.simplecs")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}


