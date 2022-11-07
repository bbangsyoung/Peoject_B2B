package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //JPA
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() { //AuditorAwared(등록자/수정자)을 빈으로 등록
        return new AuditorAwareImpl();
    }
}

