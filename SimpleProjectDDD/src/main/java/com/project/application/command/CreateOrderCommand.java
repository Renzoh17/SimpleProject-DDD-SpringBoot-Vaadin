package com.project.application.command;

import com.project.domain.entity.OrderItem;
import com.project.domain.valueobject.Quantity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class CreateOrderCommand {
    private final String customerId;
    private final List<OrderItemData> items;

    public CreateOrderCommand(String customerId, List<OrderItemData> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public List<OrderItem> toDomainItems(){
        return items.stream()
                .map(data -> new OrderItem(
                                    data.getProductId(),
                                    new Quantity(data.getQuantity()),
                                    data.getPrice()))
                .collect(Collectors.toList());
    }

    public String getCustomerId() { return customerId; }
    public List<OrderItemData> getItems() { return items; }

    public static class OrderItemData {
        private final String productId;
        private final int quantity;
        private final BigDecimal price;

        public OrderItemData(String productId, int quantity, BigDecimal price) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductId() { return productId; }
        public int getQuantity() { return quantity; }
        public BigDecimal getPrice() { return price; }
    }
}
