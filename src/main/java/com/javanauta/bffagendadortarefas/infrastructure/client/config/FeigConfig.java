package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeigConfig {

    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}
