package com.project.domain.aggregate;

import java.util.Objects;
import java.util.UUID;

public final class OrderId {
    private final String value;

    public OrderId(String value) {
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("El id de orden no puede ser nulo o vacio");
        }
        this.value = value;
    }

    public static OrderId generateNew(){
        return new OrderId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderId id = (OrderId) obj;
        if (value == null) {
            if (id.value != null)
                return false;
        }
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
