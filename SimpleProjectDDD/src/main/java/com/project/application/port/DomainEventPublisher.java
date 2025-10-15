package com.project.application.port;

import java.util.List;

public interface DomainEventPublisher {
    void publish(List<Object> events);
}
