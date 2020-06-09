package com.weronika.nask;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("test")
public class TestAppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return Mockito.mock(RestTemplate.class);
    }
}
