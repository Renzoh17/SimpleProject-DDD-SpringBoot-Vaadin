package com.project.application.query;

import java.math.BigDecimal;
import java.time.Instant;

public final class OrderSummaryDTO {
    private final String orderId;
    private final String customerId;
    private final String status;
    private final BigDecimal totalAmount;
    private final Instant creationDate;

    public OrderSummaryDTO(String orderId, String customerId, String status, BigDecimal totalAmount, Instant creationDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.creationDate = creationDate;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public String getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public Instant getCreationDate() { return creationDate; }
}
