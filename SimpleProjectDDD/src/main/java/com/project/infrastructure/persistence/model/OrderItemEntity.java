package com.project.infrastructure.persistence.model;

import com.project.domain.entity.OrderItem;
import com.project.domain.valueobject.Quantity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private String productId;
    private int quantity;
    private BigDecimal price;

    public OrderItemEntity() { }

    // Mapeo simplificado (De Dominio a Persistencia)
    public static OrderItemEntity fromDomain(OrderItem orderItem, OrderEntity orderEntity){
        OrderItemEntity entity = new OrderItemEntity();
        // Nota: no asignamos 'id' si es generado por la DB
        entity.setProductId(orderItem.getProductId());
        entity.setQuantity(orderItem.getQuantity().getValue());
        entity.setPrice(orderItem.getPrice());
        entity.setOrder(orderEntity); // Establecer la relación bidireccional

        return entity;
    }

    // Mapeo simplificado (De Persistencia a Dominio)
    public OrderItem toDomain(){
        return new OrderItem(productId, new Quantity(quantity), price);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
