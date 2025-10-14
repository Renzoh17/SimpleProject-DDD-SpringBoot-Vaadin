package com.project.domain.valueobject;

import java.util.Objects;

public final class Quantity {
    private final int value;

    public Quantity(int value) {
        if(value <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 0");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity quantity = (Quantity) obj;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
