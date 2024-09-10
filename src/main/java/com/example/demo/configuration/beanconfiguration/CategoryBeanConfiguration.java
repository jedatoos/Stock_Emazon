package com.example.demo.configuration.beanconfiguration;


import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.api.usecase.CategoryUseCase;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.ports.persistence.mysql.adapter.CategoryAdapter;
import com.example.demo.ports.persistence.mysql.mapper.ICategoryEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
