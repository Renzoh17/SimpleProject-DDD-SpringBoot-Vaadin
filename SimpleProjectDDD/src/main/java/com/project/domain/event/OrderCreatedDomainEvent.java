package com.project.domain.event;

import com.project.domain.aggregate.OrderId;

import java.time.Instant;

public class OrderCreatedDomainEvent {
    private final OrderId orderId;
    private final Instant occurredOn;

    public OrderCreatedDomainEvent(OrderId orderId) {
        this.orderId = orderId;
        this.occurredOn = Instant.now();
    }

    public OrderId getOrderId() { return orderId; }
    public Instant getOccurredOn() { return occurredOn; }
}
