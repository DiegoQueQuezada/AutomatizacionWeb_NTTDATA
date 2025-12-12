package Principal.StepsDefinitions;

import Principal.Steps.LoginSteps;
import Principal.Utils.TestContext;
import io.cucumber.java.es.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

import static Principal.Core.DriverManager.getDriver;
import static Principal.Core.DriverManager.screenShot;

public class LoginStepsDef {
    private WebDriver driver;
    private Scenario scenario;
    private LoginSteps loginSteps;

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
        TestContext.setCurrentScenarioName(scenario.getName());
        TestContext.reset(); // Resetear al inicio de cada escenario
    }

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store");
        screenShot(scenario, "01_Pagina_Tienda");
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        LoginSteps loginSteps = new LoginSteps(driver);

        // PRIMERO: Ir a la página de login (¡esto es lo que falta!)
        loginSteps.goToLoginPage();
        screenShot(scenario, "02_Pagina_Login");

        // SEGUNDO: Ingresar credenciales
        loginSteps.typeUser(usuario);
        loginSteps.typePassword(clave);
        screenShot(scenario, "03_Credenciales_Ingresadas");

        // TERCERO: Hacer login
        loginSteps.login();

        // OPCIONAL: Validar si el login fue exitoso (usando tu método)
        boolean loginExitoso = loginSteps.isLoginSuccessful();
        if (loginExitoso) {
            screenShot(scenario, "04_Login_Exitoso");
        } else {
            screenShot(scenario, "04_Login_Fallido");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}