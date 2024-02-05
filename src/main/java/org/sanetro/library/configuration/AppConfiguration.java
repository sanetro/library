package org.sanetro.library.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.sanetro.library")
public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
