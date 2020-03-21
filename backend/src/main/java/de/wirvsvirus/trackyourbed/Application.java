package de.wirvsvirus.trackyourbed;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class and spring configuration.
 */
@SpringBootApplication
public class Application {

  public static void main(final String... args) {
    setGermanLocaleToEnsureCorrectFormatting();
    SpringApplication.run(Application.class, args);
  }

  private static void setGermanLocaleToEnsureCorrectFormatting() {
    Locale.setDefault(Locale.GERMANY);
  }
}
