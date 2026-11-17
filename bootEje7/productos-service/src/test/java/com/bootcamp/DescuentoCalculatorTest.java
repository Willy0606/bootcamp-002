package com.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DescuentoCalculatorTest {

    private DescuentoCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DescuentoCalculator();
    }

    @Test
    void debeCalcularElDescuentoDeUnProducto() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("16");

        BigDecimal descuento = calculator.calcularDescuento(
                precio,
                porcentajeDescuento
        );

        System.out.println(
                "Precio: " + precio
                        + ", descuento aplicado: " + descuento
        );

        assertEquals(
                new BigDecimal("16.00"),
                descuento
        );
    }

    @Test
    void debeCalcularElPrecioTotalConDescuento() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("16");

        BigDecimal precioFinal =
                calculator.calcularPrecioConDescuento(
                        precio,
                        porcentajeDescuento
                );

        System.out.println(
                "Precio final con descuento: " + precioFinal
        );

        assertEquals(
                new BigDecimal("84.00"),
                precioFinal
        );
    }

    @Test
    void debeRechazarUnPrecioNegativo() {
        BigDecimal precio = new BigDecimal("-10.00");
        BigDecimal porcentajeDescuento = new BigDecimal("16");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularDescuento(
                        precio,
                        porcentajeDescuento
                )
        );

        System.out.println(
                "Excepción validada: " + excepcion.getMessage()
        );

        assertEquals(
                "El precio no puede ser negativo",
                excepcion.getMessage()
        );
    }

    @Test
    void debeRechazarUnPrecioNulo() {
        BigDecimal porcentajeDescuento = new BigDecimal("16");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularDescuento(
                        null,
                        porcentajeDescuento
                )
        );

        System.out.println(
                "Excepción validada: " + excepcion.getMessage()
        );

        assertEquals(
                "El precio no puede ser nulo",
                excepcion.getMessage()
        );
    }

    @Test
    void debeRegresarCeroCuandoElPrecioEsCero() {
        BigDecimal precio = new BigDecimal("0.00");
        BigDecimal porcentajeDescuento = new BigDecimal("16");

        BigDecimal descuento = calculator.calcularDescuento(
                precio,
                porcentajeDescuento
        );

        System.out.println(
                "El descuento debe ser igual a 0: " + descuento
        );

        assertEquals(
                new BigDecimal("0.00"),
                descuento
        );
    }

    @Test
    void debeRegresarElMismoPrecioCuandoElDescuentoEsCero() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("0");

        BigDecimal precioFinal =
                calculator.calcularPrecioConDescuento(
                        precio,
                        porcentajeDescuento
                );

        System.out.println(
                "Precio sin descuento: " + precioFinal
        );

        assertEquals(
                new BigDecimal("100.00"),
                precioFinal
        );
    }

    @Test
    void debeRechazarUnDescuentoNegativo() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("-10");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularDescuento(
                        precio,
                        porcentajeDescuento
                )
        );

        System.out.println(
                "Excepción validada: " + excepcion.getMessage()
        );

        assertEquals(
                "El porcentaje de descuento no puede ser negativo",
                excepcion.getMessage()
        );
    }

    @Test
    void debeRechazarUnDescuentoMayorA100() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("101");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularDescuento(
                        precio,
                        porcentajeDescuento
                )
        );

        System.out.println(
                "Excepción validada: " + excepcion.getMessage()
        );

        assertEquals(
                "El porcentaje de descuento no puede ser mayor a 100",
                excepcion.getMessage()
        );
    }

    @Test
    void debeRegresarCeroConUnDescuentoDel100PorCiento() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeDescuento = new BigDecimal("100");

        BigDecimal precioFinal =
                calculator.calcularPrecioConDescuento(
                        precio,
                        porcentajeDescuento
                );

        System.out.println(
                "Precio final con descuento del 100%: " + precioFinal
        );

        assertEquals(
                new BigDecimal("0.00"),
                precioFinal
        );
    }
}