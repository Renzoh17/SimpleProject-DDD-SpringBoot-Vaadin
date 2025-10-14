package com.project.domain.service;

import com.project.domain.aggregate.Order;

import java.math.BigDecimal;
import java.util.Optional;

public class PricingDomainService {

    public BigDecimal calculateFinalPrice(Order order){
        BigDecimal subtotal = order.calculateTotal();

        if(subtotal.compareTo(new BigDecimal("1000")) > 0){
            // Regla de dominio: Descuento del 10% para órdenes grandes
            return subtotal.multiply(new BigDecimal("0.90"));
        }

        // Simulación de interacción con un servicio externo o política
        Optional<BigDecimal> taxRate = getTaxRateByRegion(order.getShippingAddress().getCity());
        if(taxRate.isPresent()){
            subtotal = subtotal.add(subtotal.multiply(taxRate.get()));
        }

        return subtotal;
    }

    // Lógica privada del servicio, podría ser un puerto a otro Bounded Context
    private Optional<BigDecimal> getTaxRateByRegion(String city){
        // En un caso real, esto llamaría a un servicio de impuestos,
        // pero se simula la lógica aquí:
        if ("Buenos Aires".equalsIgnoreCase(city)) {
            return Optional.of(new BigDecimal("0.21"));
        }
        return Optional.empty();
    }
}
