package app.game.carmensandiego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired GameManager gameManager;

    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        gameManager.play();
    }
}
