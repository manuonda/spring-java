package com.pattern;

public class FactoryMain {

    public static void main(String args[]) {
      Producto productoA = FactoryProducto.createProducto("A");
        productoA.operation();
      ProductoB productoB = (ProductoB) FactoryProducto.createProducto("B");
      productoB.operation();
    }
}
