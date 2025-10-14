package com.project.domain.entity;

import com.project.domain.valueobject.Quantity;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private final String productId;
    private Quantity quantity;
    private BigDecimal price;

    public OrderItem(String productId, Quantity quantity, BigDecimal price) {
        this.productId = Objects.requireNonNull(productId, "El id del producto es requerido");
        this.quantity = Objects.requireNonNull(quantity, "La cantidad es requerida");
        this.price = Objects.requireNonNull(price, "El precio es requerido");
    }

    public BigDecimal calculateSubTotal(){
        return price.multiply(new BigDecimal(quantity.getValue()));
    }

    public String getProductId() { return productId; }
    public Quantity getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
}
