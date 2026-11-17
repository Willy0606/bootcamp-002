package com.bootcamp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescuentoCalculator {

    private static final BigDecimal CIEN = new BigDecimal("100");

    /**
     * Calcula el importe del descuento.
     *
     * Ejemplo:
     * precio = 100.00
     * porcentajeDescuento = 16
     * resultado = 16.00
     */
    public BigDecimal calcularDescuento(
            BigDecimal precio,
            BigDecimal porcentajeDescuento) {

        validarDatos(precio, porcentajeDescuento);

        return precio
                .multiply(porcentajeDescuento)
                .divide(CIEN, 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula el precio final después de aplicar el descuento.
     *
     * Ejemplo:
     * precio = 100.00
     * porcentajeDescuento = 16
     * resultado = 84.00
     */
    public BigDecimal calcularPrecioConDescuento(
            BigDecimal precio,
            BigDecimal porcentajeDescuento) {

        validarDatos(precio, porcentajeDescuento);

        BigDecimal descuento = calcularDescuento(
                precio,
                porcentajeDescuento
        );

        return precio
                .subtract(descuento)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void validarDatos(
            BigDecimal precio,
            BigDecimal porcentajeDescuento) {

        if (precio == null) {
            throw new IllegalArgumentException(
                    "El precio no puede ser nulo"
            );
        }

        if (porcentajeDescuento == null) {
            throw new IllegalArgumentException(
                    "El porcentaje de descuento no puede ser nulo"
            );
        }

        if (precio.signum() < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo"
            );
        }

        if (porcentajeDescuento.signum() < 0) {
            throw new IllegalArgumentException(
                    "El porcentaje de descuento no puede ser negativo"
            );
        }

        if (porcentajeDescuento.compareTo(CIEN) > 0) {
            throw new IllegalArgumentException(
                    "El porcentaje de descuento no puede ser mayor a 100"
            );
        }
    }
}