package org.wscale.mountains.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.wscale.mountains.repositories.MountainRepository;

@Configuration
public class BusinessConfig {

    @Bean
    public MountainRepository mountainRepository() { return mock(MountainRepository.class); }
}
