package com.tms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //этот класс используется для настройки приложения
public class SpringConfig {

    @Bean
    public Cap getCapBean(){
        return new Cap("from Java");
    }
}
