package Principal.Steps;
import Principal.Page.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCartPageTitle() {
        try {
            WebElement titleElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(CartPage.cartTitle)
            );
            return titleElement.getText();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }
}