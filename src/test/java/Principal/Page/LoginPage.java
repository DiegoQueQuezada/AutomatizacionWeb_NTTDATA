package Principal.Page;

import org.openqa.selenium.By;

public class LoginPage {

    public static By loginLink = By.cssSelector("a[title='Acceda a su cuenta de cliente']");
    public static By userInput = By.id("field-email");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");
}
