package com.classeye.classservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // to indicate that this class is a configuration class which will be used to configure the application
@EnableJpaAuditing // to enable JPA Auditing to automatically populate the createdBy and updatedBy fields
class Config {
    //    @Bean
    //    public AuditorAware<AuditableUser> auditorProvider() {
    //        return new AuditorAwareImpl();
    //    }
}
