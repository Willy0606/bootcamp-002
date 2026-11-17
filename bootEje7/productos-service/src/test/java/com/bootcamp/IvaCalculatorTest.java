package com.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IvaCalculatorTest {

    private IvaCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new IvaCalculator();
    }

    @Test
    void debeCalcularElIvaDeUnProducto() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeIva = new BigDecimal("16");

        BigDecimal iva = calculator.calcularIva(precio, porcentajeIva);
        
        System.out.println("El iva para: "+ precio + " iva: " + iva);

        assertEquals(new BigDecimal("16.00"), iva);
    }

    @Test
    void debeCalcularElPrecioTotalConIva() {
        BigDecimal precio = new BigDecimal("100.00");
        BigDecimal porcentajeIva = new BigDecimal("16");

        BigDecimal precioTotal =
                calculator.calcularPrecioConIva(precio, porcentajeIva);
        
        System.out.println("Precio total con iva: " + precioTotal);

        assertEquals(new BigDecimal("116.00"), precioTotal);
    }

    @Test
    void debeRechazarUnPrecioNegativo() {
        BigDecimal precio = new BigDecimal("-10.00");
        BigDecimal porcentajeIva = new BigDecimal("16");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularIva(precio, porcentajeIva)
        );

        System.out.println("Excepción validada: " + excepcion.getMessage());

        assertEquals(
                "El precio no puede ser negativo",
                excepcion.getMessage()
        );
    }

    @Test
    void debeRechazarValoresNulos() {
    	
    	System.out.println("El precio no puede ser un valor nulo");
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calcularIva(null, new BigDecimal("16"))
        );
    }
    
    @Test
    void debeRegresarCeroCuandoElPrecioEsCero() {
        BigDecimal precio = new BigDecimal("0.00");
        BigDecimal porcentajeIva = new BigDecimal("16");

        BigDecimal iva = calculator.calcularIva(precio, porcentajeIva);
        
        System.out.println("El valor no debe ser igual a 0: " + iva);

        assertEquals(new BigDecimal("0.00"), iva);
    }
}