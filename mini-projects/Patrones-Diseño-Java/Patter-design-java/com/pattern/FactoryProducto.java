package com.pattern;

public class FactoryProducto {
    public static Producto createProducto(String tipo) {
        switch (tipo) {
            case  "A" : return new ProductoA();
            case "B" : return new ProductoB();
            default:
                throw new IllegalArgumentException("Tipo de Producto No Valido");
        }

    }
}
