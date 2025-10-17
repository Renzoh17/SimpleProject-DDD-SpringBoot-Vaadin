package com.project.infrastructure.persistence.repository;

import com.project.domain.aggregate.Order;
import com.project.domain.aggregate.OrderId;
import com.project.domain.repository.OrderRepository;
import com.project.infrastructure.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {}

@Repository
public class JpaOrderRepositoryImpl implements OrderRepository {
    private final SpringDataOrderRepository jpaRepository;

    public JpaOrderRepositoryImpl(SpringDataOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpaRepository.findById(id.getValue())
                .map(OrderEntity::toDomain);
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = OrderEntity.fromDomain(order);

        jpaRepository.save(entity);
    }
}
