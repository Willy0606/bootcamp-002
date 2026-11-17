package com.bootcamp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IvaCalculator {

    private static final BigDecimal CIEN = new BigDecimal("100");

    /**
     * Calcula el importe del IVA de un producto.
     *
     * @param precio precio del producto
     * @param porcentajeIva porcentaje de IVA, por ejemplo 16
     * @return importe correspondiente al IVA
     */
    public BigDecimal calcularIva(
            BigDecimal precio,
            BigDecimal porcentajeIva) {

        validarDatos(precio, porcentajeIva);

        return precio
                .multiply(porcentajeIva)
                .divide(CIEN, 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula el precio total del producto incluyendo el IVA.
     *
     * @param precio precio del producto
     * @param porcentajeIva porcentaje de IVA
     * @return precio total con IVA
     */
    public BigDecimal calcularPrecioConIva(
            BigDecimal precio,
            BigDecimal porcentajeIva) {

        validarDatos(precio, porcentajeIva);

        BigDecimal iva = calcularIva(precio, porcentajeIva);

        return precio
                .add(iva)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void validarDatos(
            BigDecimal precio,
            BigDecimal porcentajeIva) {

        if (precio == null) {
            throw new IllegalArgumentException(
                    "El precio no puede ser nulo"
            );
        }

        if (porcentajeIva == null) {
            throw new IllegalArgumentException(
                    "El porcentaje de IVA no puede ser nulo"
            );
        }

        if (precio.signum() < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo"
            );
        }

        if (porcentajeIva.signum() < 0) {
            throw new IllegalArgumentException(
                    "El porcentaje de IVA no puede ser negativo"
            );
        }
    }
}