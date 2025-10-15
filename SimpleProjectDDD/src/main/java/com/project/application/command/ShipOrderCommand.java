package com.project.application.command;

public final class ShipOrderCommand {
    private final String orderId;
    private final String street;
    private final String city;
    private final String zipCode;

    public ShipOrderCommand(String orderId, String street, String city, String zipCode) {
        this.orderId = orderId;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getOrderId() { return orderId; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }
}
