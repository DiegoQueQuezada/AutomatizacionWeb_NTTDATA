package Principal.Steps;

import Principal.Page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private static double productPrice;
    private static String productName;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCategory(String category, String subcategory) {
        try {

            if (category.equals("Clothes")) {


                WebElement clothesLink = wait.until(
                        ExpectedConditions.elementToBeClickable(StorePage.clothesCategory)
                );

                clothesLink.click();

                // Esperar que cargue
                wait.until(ExpectedConditions.urlContains("3-clothes"));

                // NAVEGAR A MEN
                if (subcategory.equals("Men")) {
                    WebElement menLink = wait.until(
                            ExpectedConditions.elementToBeClickable(StorePage.menSubcategory)
                    );
                    menLink.click();

                    // Esperar que cargue
                    wait.until(ExpectedConditions.urlContains("4-men"));
                    wait.until(ExpectedConditions.presenceOfElementLocated(StorePage.categoryTitle));
                }
            } else if (category.equals("Autos")) {
                throw new RuntimeException("Categoría 'Autos' no encontrada - Comportamiento esperado");
            }
        } catch (TimeoutException e) {
            navigateToCategoryAlternative(category, subcategory);
        }
    }

    private void navigateToCategoryAlternative(String category, String subcategory) {
        // Usar URLs directas
        if (category.equals("Clothes") && subcategory.equals("Men")) {
            driver.get("https://qalab.bensg.com/store/pe/4-men");
            wait.until(ExpectedConditions.urlContains("4-men"));
        } else if (category.equals("Clothes")) {
            driver.get("https://qalab.bensg.com/store/pe/3-clothes");
        }
    }

    public void addFirstProductToCart(int quantity) {
        try {
            openFirstProductMenu();

            WebElement precioElement = driver.findElement(By.cssSelector(".current-price-value"));
            String precioText = precioElement.getAttribute("content"); // "19.12"
            this.productPrice = Double.parseDouble(precioText);
            WebElement aumentarBtn = driver.findElement(StorePage.botonIncrementarProducto);
            for (int i = 0; i < quantity-1; i++) {
                aumentarBtn.click();
                Thread.sleep(300);
            }
            driver.findElement(StorePage.botonAñadirCarrito).click();
            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public boolean isAddToCartPopupVisible() {
        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement popup = shortWait.until(
                    ExpectedConditions.presenceOfElementLocated(StorePage.menuFinalizarCompra)
            );
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public double getPopupTotalAmount(By elemento) {
        try {
            WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
            String totalText = totalElement.getText(); // "S/&nbsp;267.68"
            totalText = totalText.replace("S/", "")
                    .replace("&nbsp;", "")
                    .replace(",", "")
                    .trim();
            double total = Double.parseDouble(totalText); // 267.68
            return total;

        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo total: " + e.getMessage());
        }
    }

    public void goToCart() {
        try {

            WebElement finalizarBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(StorePage.finalizarCompraButton)
            );

            finalizarBtn.click();
            wait.until(ExpectedConditions.urlContains("/carrito"));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".cart-container")
            ));

        } catch (Exception e) {
            throw new RuntimeException("Error yendo al carrito: " + e.getMessage());
        }
    }

    public void openFirstProductMenu() {
        try {
            WebElement productThumbnail = wait.until(
                    ExpectedConditions.elementToBeClickable(StorePage.botonAbrirProducto)
            );
            productThumbnail.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.quantityInput));
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Error abriendo menú del producto: " + e.getMessage());
        }
    }
}