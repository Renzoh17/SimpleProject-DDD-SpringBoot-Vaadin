package com.project.domain.valueobject;

import java.util.Objects;

public final class Address {
    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = Objects.requireNonNull(street, "La direccion no puede ser nula");
        this.city = Objects.requireNonNull(city, "La ciudad no puede ser nula");
        this.zipCode = Objects.requireNonNull(zipCode, "El codigo postal no puede ser nulo");
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}
