package com.project.domain.aggregate;

import com.project.domain.entity.OrderItem;
import com.project.domain.event.OrderShippedDomainEvent;
import com.project.domain.valueobject.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final OrderId id;
    private String customerId;
    private OrderStatus status;
    private List<OrderItem> items;
    private Address shippingAddress;

    private final List<Object> domainEvents = new ArrayList<>();


    private Order(OrderId id, String customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.status = OrderStatus.PENDING;
    }

    public static Order create(String customerId, List<OrderItem> initialItems){
        if(initialItems == null || initialItems.isEmpty()){
            throw new IllegalArgumentException("La lista de items debe contener al menos un item");
        }

        return new Order(OrderId.generateNew(), customerId, initialItems);
    }

    public void ship(Address address){
        if(!status.canBeShipped()){
            throw new IllegalStateException("El pedido no puede ser enviado ya que está en estado " + status);
        }
        if(address == null){
            throw new IllegalArgumentException("La dirección es requerida");
        }

        this.status = OrderStatus.SHIPPED;
        this.shippingAddress = address;

        //Registrar el evento de dominio
        // El agregado genera el evento después de cambiar su propio estado
        this.domainEvents.add(new OrderShippedDomainEvent(this.id));
    }

    public BigDecimal calculateTotal(){
        return items.stream()
                .map(OrderItem::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearDomainEvents(){
        this.domainEvents.clear();
    }

    public List<Object> getDomainEvents(){ return Collections.unmodifiableList(domainEvents); }
    public OrderId getId() { return id; }
    public String getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public Address getShippingAddress() { return shippingAddress; }

}
