package Principal.Page;

import org.openqa.selenium.By;

public class CartPage {

    public static By cartTitle = By.cssSelector(".cart-container .h1");
    public static By cartItemName = By.cssSelector(".cart-item .product-name");
    public static By cartItemQuantity = By.cssSelector(".cart-item .js-cart-line-product-quantity");
    public static By cartSubtotal = By.cssSelector(".cart-summary-line .value");
    public static By cartTotalIncl = By.xpath("//span[text()='Total (impuestos incl.)']/following-sibling::span");

}
