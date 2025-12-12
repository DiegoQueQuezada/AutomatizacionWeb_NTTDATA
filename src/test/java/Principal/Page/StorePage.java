package Principal.Page;

import org.openqa.selenium.By;

public class StorePage {
    // Categorias
    public static By clothesCategory = By.cssSelector("a[href='https://qalab.bensg.com/store/pe/3-clothes']");
    public static By menSubcategory = By.cssSelector("a[href*='/4-men']");

    // Productos (primer producto de la página)
    public static By firstProduct = By.cssSelector(".products article:first-child");
    public static By firstProductName = By.cssSelector(".products article:first-child .product-title a");
    public static By firstProductPrice = By.cssSelector(".products article:first-child .product-price");

    // NUEVO: Botón para abrir el menú del producto
    public static By botonAbrirProducto = By.cssSelector(".product-thumbnail");

    // Cantidad y botones de incremento - EXACTOS según tu descripción
    public static By quantityInput = By.id("quantity_wanted");
    public static By botonIncrementarProducto = By.cssSelector(".bootstrap-touchspin-up");


    // Botón agregar al carrito
    public static By botonAñadirCarrito = By.cssSelector(".btn-primary.add-to-cart");


    public static By menuFinalizarCompra = By.id("blockcart-modal");
    public static By elementoPrecioTotal = By.cssSelector(".subtotal.value");
    public static By elementoPrecioTotalFinal = By.cssSelector("#cart-subtotal-products .value");

    public static By elementoPrecioBase = By.cssSelector(".current-price-value");
    public static By finalizarCompraButton = By.xpath("//a[contains(@href, '/carrito') and contains(text(), 'Finalizar compra')]");

    // Página del carrito
    public static By cartTitle = By.cssSelector("h1.h1");
    public static By cartTotal = By.cssSelector(".cart-summary .value");

    // Para validar que está en la página de categoría
    public static By categoryTitle = By.tagName("h1");
}