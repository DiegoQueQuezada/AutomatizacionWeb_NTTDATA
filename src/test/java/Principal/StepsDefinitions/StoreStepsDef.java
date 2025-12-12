package Principal.StepsDefinitions;

import Principal.Steps.*;
import io.cucumber.java.es.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

import static Principal.Core.DriverManager.getDriver;
import static Principal.Core.DriverManager.screenShot;
import static Principal.Page.StorePage.*;

public class StoreStepsDef {
    private WebDriver driver;
    private Scenario scenario;
    private StoreSteps storeSteps;
    private CartSteps cartSteps;
    private int cantidad;
    private double precioCalculado;

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {

        if (driver == null) {
            driver = getDriver();
        }

        storeSteps = new StoreSteps(driver);
        try {
            storeSteps.navigateToCategory(categoria, subcategoria);
            screenShot(scenario, "05_Categoria_" + categoria + "_" + subcategoria);
        } catch (Exception e) {
            if (categoria.equals("Autos") || categoria.equals("Cars")) {
                screenShot(scenario, "05_Categoria_Inexistente_Esperado");
                Assertions.fail("Categoría '" + categoria + "' no existe - Comportamiento esperado");
            }
            throw e;
        }
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int cantidad) {
        storeSteps.addFirstProductToCart(cantidad);
        this.cantidad = cantidad;
        screenShot(scenario, "06_Producto_Agregado_Cantidad_" + cantidad);
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        storeSteps = new StoreSteps(driver);
        boolean isPopupVisible = storeSteps.isAddToCartPopupVisible();
        Assertions.assertTrue(isPopupVisible, "Popup de confirmación no visible");
        screenShot(scenario, "07_Popup_Confirmacion_Visible");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {

        storeSteps = new StoreSteps(driver);
        double popupTotal = storeSteps.getPopupTotalAmount(elementoPrecioTotal);
        double productPrice = storeSteps.getPopupTotalAmount(elementoPrecioBase);
        precioCalculado = productPrice * cantidad;

        Assertions.assertEquals(precioCalculado, popupTotal, 0.01,
                () -> String.format("Total en popup incorrecto. Esperado: %.2f, Actual: %.2f",
                        precioCalculado, popupTotal));

        screenShot(scenario, "08_Validacion_Total_Popup");
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        storeSteps = new StoreSteps(driver);
        storeSteps.goToCart();
        screenShot(scenario, "09_Pagina_Carrito_Cargada");
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        cartSteps = new CartSteps(driver);
        String cartTitle = cartSteps.getCartPageTitle();
        boolean isTitleValid = cartTitle.contains("CARRITO");

        Assertions.assertTrue(isTitleValid,
                () -> String.format("Título incorrecto: %s", cartTitle));

        screenShot(scenario, "10_Titulo_Carrito_Validado");
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        cartSteps = new CartSteps(driver);
        double cartTotal = storeSteps.getPopupTotalAmount(elementoPrecioTotalFinal);
        Assertions.assertEquals(precioCalculado, cartTotal, 0.01,
                () -> String.format("Total en carrito incorrecto. Esperado: %.2f, Actual: %.2f",
                        precioCalculado, cartTotal));
        screenShot(scenario, "11_Validacion_Final_Carrito");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            screenShot(scenario, "FAILED_" + scenario.getName());
        }
    }
}