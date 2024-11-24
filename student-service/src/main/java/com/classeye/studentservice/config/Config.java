package com.classeye.studentservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author moham
 **/
@Configuration
@EnableJpaAuditing
class Config {

//    @Bean
//    public AuditorAware<AuditableUser> auditorProvider() {
//        return new AuditorAwareImpl();
//    }
}
