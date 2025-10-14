package com.project.domain.aggregate;

public enum OrderStatus {
    PENDING,
    SHIPPED,
    CANCELED,
    DELIVERED;

    public boolean canBeShipped(){
        return this == PENDING;
    }
}
