package Principal.Steps;

import Principal.Page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToLoginPage() {
        WebElement loginLink = wait.until(
                ExpectedConditions.elementToBeClickable(LoginPage.loginLink)
        );
        loginLink.click();
        wait.until(ExpectedConditions.urlContains("iniciar-sesion"));
    }

    public void typeUser(String user) {
        WebElement userField = wait.until(
                ExpectedConditions.elementToBeClickable(LoginPage.userInput)
        );
        userField.clear();
        userField.sendKeys(user);
    }

    public void typePassword(String password) {
        driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void login() {
        driver.findElement(LoginPage.loginButton).click();
    }

    public boolean isLoginSuccessful() {
        try {

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("my-account"),
                    ExpectedConditions.urlContains("cuenta"),
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector(".account span")
                    ),
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector(".user-info")
                    )
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}