package com.project.application.query;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Servicio dedicado a realizar consultas (lectura de datos).
 * Típicamente, esto lee directamente de la base de datos o de un modelo de lectura optimizado
 * (sin pasar por el modelo de Dominio).
 */
@Service
public class OrderQueryService {
    // Inyectar un puerto de Infraestructura para consultas optimizadas (ej. OrderReadRepository)
    // private final OrderReadRepository readRepository;

    // public OrderQueryService(OrderReadRepository readRepository) {
    //     this.readRepository = readRepository;
    // }

    /**
     * Consulta: Obtiene una lista paginada de resúmenes de órdenes.
     */
    public List<OrderSummaryDTO> getOrderSummaries(int page, int pageSize) {
        // En una implementación real, esto llamaría a:
        // return readRepository.findAllSummaries(page, size);

        // Simulación:
        return List.of(
                new OrderSummaryDTO("ORD-001", "CUST-1", "SHIPPED", new BigDecimal("150.75"), Instant.now()),
                new OrderSummaryDTO("ORD-002", "CUST-2", "PENDING", new BigDecimal("45.00"), Instant.now().minusSeconds(3600))
        );
    }
}
