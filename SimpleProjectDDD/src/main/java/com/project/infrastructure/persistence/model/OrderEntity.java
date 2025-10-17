package com.project.infrastructure.persistence.model;

import com.project.domain.aggregate.Order;
import com.project.domain.aggregate.OrderId;
import com.project.domain.aggregate.OrderStatus;
import com.project.domain.entity.OrderItem;
import com.project.domain.valueobject.Address;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String id;
    private String customerId;
    private String status;

    private BigDecimal totalAmount;
    private String shippingStreet;
    private String shippingCity;
    private String shippingZipCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    public static OrderEntity fromDomain(Order domainOrder){
        OrderEntity entity = new OrderEntity();

        entity.setId(domainOrder.getId().getValue());
        entity.setCustomerId(domainOrder.getCustomerId());
        entity.setStatus(domainOrder.getStatus().name());

        Address domainAddress = domainOrder.getShippingAddress();
        if(domainAddress != null){
            entity.setShippingStreet(domainAddress.getStreet());
            entity.setShippingCity(domainAddress.getCity());
            entity.setShippingZipCode(domainAddress.getZipCode());
        }

        List<OrderItemEntity> itemEntities = domainOrder.getItems().stream()
                .map(item -> OrderItemEntity.fromDomain(item, entity))
                .collect(Collectors.toList());
        entity.setItems(itemEntities);

        return entity;
    }

    public Order toDomain(){
        Address domainAddress = null;
        if(shippingStreet != null){
            domainAddress = new Address(shippingStreet, shippingCity, shippingZipCode);
        }

        List<OrderItem> domainItems = items.stream()
                .map(OrderItemEntity::toDomain)
                .collect(Collectors.toList());

        return Order.reconstruct(
                new OrderId(this.id),
                this.customerId,
                domainItems,
                OrderStatus.valueOf(this.status),
                domainAddress
        );
    }

    public OrderEntity() {} // Constructor vacío requerido por JPA

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getShippingStreet() { return shippingStreet; }
    public void setShippingStreet(String shippingStreet) { this.shippingStreet = shippingStreet; }
    public String getShippingCity() { return shippingCity; }
    public void setShippingCity(String shippingCity) { this.shippingCity = shippingCity; }
    public String getShippingZipCode() { return shippingZipCode; }
    public void setShippingZipCode(String shippingZipCode) { this.shippingZipCode = shippingZipCode; }
    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; }

}
