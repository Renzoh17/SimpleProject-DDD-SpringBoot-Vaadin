package com.project.domain.repository;

import com.project.domain.aggregate.Order;
import com.project.domain.aggregate.OrderId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository {
    Optional<Order> findById(OrderId id);
    void save(Order order);
}
