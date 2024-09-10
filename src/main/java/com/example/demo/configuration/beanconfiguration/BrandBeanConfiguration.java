package com.example.demo.configuration.beanconfiguration;

import com.example.demo.domain.api.IBrandServicePort;
import com.example.demo.domain.api.usecase.BrandUseCase;
import com.example.demo.domain.spi.IBrandPersistencePort;
import com.example.demo.ports.persistence.mysql.adapter.BrandAdapter;
import com.example.demo.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BrandBeanConfiguration {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}
