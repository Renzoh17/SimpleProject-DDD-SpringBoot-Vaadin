package com.project.infrastructure.config;

import com.project.domain.service.PricingDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    // Ejemplo de cómo se definiría e inyectaría el Domain Service.
    // Aunque Spring podría encontrarlo con @Service, definirlo aquí puede ser útil
    // si necesita dependencias complejas (como repositorios de otros contextos)

    @Bean
    public PricingDomainService pricingDomainService() {
        // Si el Domain Service no tiene dependencias (como este ejemplo simple):
        return new PricingDomainService();

        // Si tuviera dependencias (ej: un puerto de otro contexto), se inyectarían aquí:
        // return new PricingDomainService(discountPolicyRepository);
    }
}
