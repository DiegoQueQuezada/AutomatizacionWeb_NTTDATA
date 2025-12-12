package Principal.Page;

import org.openqa.selenium.By;

public class StorePage {

    public static By clothesCategory = By.cssSelector("a[href='https://qalab.bensg.com/store/pe/3-clothes']");
    public static By menSubcategory = By.cssSelector("a[href*='/4-men']");
    public static By botonAbrirProducto = By.cssSelector(".product-thumbnail");
    public static By quantityInput = By.id("quantity_wanted");
    public static By botonIncrementarProducto = By.cssSelector(".bootstrap-touchspin-up");
    public static By botonAñadirCarrito = By.cssSelector(".btn-primary.add-to-cart");
    public static By menuFinalizarCompra = By.id("blockcart-modal");
    public static By elementoPrecioTotal = By.cssSelector(".subtotal.value");
    public static By elementoPrecioTotalFinal = By.cssSelector("#cart-subtotal-products .value");
    public static By elementoPrecioBase = By.cssSelector(".current-price-value");
    public static By finalizarCompraButton = By.xpath("//a[contains(@href, '/carrito') and contains(text(), 'Finalizar compra')]");
    public static By categoryTitle = By.tagName("h1");

}