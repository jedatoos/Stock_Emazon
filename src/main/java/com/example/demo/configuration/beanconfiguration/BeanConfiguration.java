package com.example.demo.configuration.beanconfiguration;


import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.api.usecase.CategoryUseCase;
import com.example.demo.domain.spi.category.ICategoryPersistencePort;
import com.example.demo.ports.persistence.mysql.adapter.CategoryAdapter;
import com.example.demo.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
