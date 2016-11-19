package com.example.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class DataConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
