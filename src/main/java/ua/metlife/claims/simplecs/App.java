package ua.metlife.claims.simplecs;

import com.citi.gsp.ds.snl.dowloader.Loader;
import com.sun.javafx.util.Logging;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;


@SpringBootApplication
@EnableJpaRepositories("ua.metlife.claims.simplecs.repo")
@EntityScan(basePackages = "ua.metlife.claims.simplecs")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        Loader.testLoader();

    }



}


