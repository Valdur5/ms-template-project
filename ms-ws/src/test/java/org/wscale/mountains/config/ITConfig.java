package org.wscale.mountains.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.wscale.mountains.repositories.MountainRepository;
import org.wscale.mountains.service.MountainService;

@Configuration
public class ITConfig {

    /**
     * This method replaces the MountainService Implementation from the business layer for our integration tests with
     * an Mockito mock object. It gets automatically called when we execute our tests and want to Autowire a
     * MountainService object.
     * @return - A mock object of the MountainService Interface.
     */
    @Bean
    public MountainService mountainService(){
        return mock(MountainService.class);
    }

    @Bean
    public MountainRepository mountainRepository() { return mock(MountainRepository.class); }
}
