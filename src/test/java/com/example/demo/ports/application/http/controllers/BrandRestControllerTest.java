package com.example.demo.ports.application.http.controllers;

import com.example.demo.domain.api.IBrandServicePort;
import com.example.demo.domain.model.Brand;
import com.example.demo.ports.application.http.dto.BrandRequest;
import com.example.demo.ports.application.http.mappers.IBrandRequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest()
@ContextConfiguration(classes = BrandRestController.class)
class BrandRestControllerTest {

    @Autowired()
    private MockMvc mockMvc;

    @MockBean(name = "brandRequestMapper")
    private IBrandRequestMapper brandRequestMapperMock;

    @MockBean(name = "brandServicePort")
    private IBrandServicePort brandServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    @Test
    void saveBrandTest() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest("TechWorld", "Leading technology brand");

        String contentStr = new ObjectMapper().writeValueAsString(brandRequest);

        Brand brand = new Brand();
        doReturn(brand).when(brandRequestMapperMock).brandRequestToBrand(brandRequest);
        doNothing().when(brandServicePortMock).saveBrand(brand);

        // Act
        ResultActions resultActions = this.mockMvc.perform(post("/brand")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isCreated());
    }

    @SpringBootApplication(scanBasePackageClasses = BrandRestController.class)
    static class BrandRestControllerTestConfig {
    }
}