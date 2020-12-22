package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the class that starts whole application.
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    /**
     * Starts the program with given parameters
     *
     * @param args array of parameters for the program
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}