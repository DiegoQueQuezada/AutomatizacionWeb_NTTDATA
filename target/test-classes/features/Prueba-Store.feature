# language: es
Característica: Product - Store
  Validación del sistema de tienda online con diferentes escenarios

  Esquema del escenario: Validación del precio de un producto con diferentes credenciales
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    Y agrego <cantidad> unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | usuario          | clave          | categoria | subcategoria | cantidad | resultado       |
      | diegodavid0404@hotmail.com   | cremosho1234   | Clothes   | Men          | 2        | exitoso         |
