package com.project.application.service;

import com.project.application.command.CreateOrderCommand;
import com.project.application.command.ShipOrderCommand;
import com.project.application.port.DomainEventPublisher;
import com.project.domain.aggregate.Order;
import com.project.domain.aggregate.OrderId;
import com.project.domain.event.OrderCreatedDomainEvent;
import com.project.domain.repository.OrderRepository;
import com.project.domain.valueobject.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final DomainEventPublisher eventPublisher;

    private final List<Object> domainEvents = new ArrayList<>();

    public OrderApplicationService(OrderRepository orderRepository, DomainEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public OrderId createOrder(CreateOrderCommand command) {
        Order newOrder = Order.create(command.getCustomerId(), command.toDomainItems());
        orderRepository.save(newOrder);
        domainEvents.add(new OrderCreatedDomainEvent(newOrder.getId()));
        return newOrder.getId();
    }

    /**
     * Caso de Uso: Enviar una orden existente.
     */
    // @Transactional // Se requiere una transacción aquí
    public void shipOrder(ShipOrderCommand command) {
        OrderId orderId = new OrderId(command.getOrderId());
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("El id de orden no existe: " + orderId.getValue()));

        Address address = new Address(command.getStreet(), command.getCity(), command.getZipCode());
        order.ship(address);
        orderRepository.save(order);

        // Esto se hace DESPUÉS de guardar para garantizar que la transacción local fue exitosa.
        eventPublisher.publish(order.getDomainEvents());
        order.clearDomainEvents();

    }

    public List<Object> getDomainEvents(){ return Collections.unmodifiableList(domainEvents); }
    public void clearDomainEvents(){ domainEvents.clear(); }

    class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) { super(message); }
    }
}
