package Principal.StepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;

import static Principal.Core.DriverManager.getDriver;

public class LoginGoogleStepsDef {

    private static WebDriver driver;
    private static Scenario scenario;


    @Dado("Abro el navegador")
    public void abro_el_navegador() {
    }

    @Cuando("Navego a {string}")
    public void navego_a(String url) {
        driver = getDriver();
        driver.get(url);
    }

    @Entonces("Debería ver el título de Google")
    public void deberia_ver_el_titulo_de_google() {
        String titulo = driver.getTitle();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
