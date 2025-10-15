package com.project.infrastructure.messaging;

import com.project.application.port.DomainEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Adaptador que implementa el puerto DomainEventPublisher, usando Kafka (simulado).
 */
@Component // Componente de Spring para Inyección de Dependencias
public class KafkaDomainEventPublisher implements DomainEventPublisher {
    // En un proyecto real, inyecto KafkaTemplate o algún cliente de mensajería
    // private final KafkaTemplate<String, Object> kafkaTemplate;

    // public KafkaDomainEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
    //     this.kafkaTemplate = kafkaTemplate;
    // }

    @Override
    public void publish(List<Object> events) {
        if(events.isEmpty()){
            return;
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("INFRASTRUCTURE: Publishing " + events.size() + " events to Kafka");

        for(Object event : events){
            // Aquí se serializaría el evento y se enviaría a Kafka
            System.out.println("  -> Published Event: " + event.getClass().getSimpleName()
                    + " for OrderId: " + getOrderIdFromEvent(event));
            // kafkaTemplate.send("domain-events-topic", event);
        }
        System.out.println("-----------------------------------------------------");

    }

    private String getOrderIdFromEvent(Object event){

        try{
            // Reflexión simple para fines de demostración
            return event.getClass().getMethod("getOrderId").invoke(event).toString();
        } catch(Exception e) {
            return "[Error al obtener ID]";
        }

    }
}
