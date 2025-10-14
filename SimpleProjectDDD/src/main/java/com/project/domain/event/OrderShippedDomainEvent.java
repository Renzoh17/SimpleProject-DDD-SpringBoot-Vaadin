package com.project.domain.event;

import com.project.domain.aggregate.OrderId;

import java.time.Instant;
import java.util.Objects;

public final class OrderShippedDomainEvent {
    private final OrderId orderId;
    private final Instant occurredOn;

    public OrderShippedDomainEvent(OrderId orderId) {
        this.orderId = Objects.requireNonNull(orderId, "El id de orden es requerido");
        this.occurredOn = Instant.now();
    }

    public OrderId getOrderId() { return orderId; }
    public Instant getOccurredOn() { return occurredOn; }
}
