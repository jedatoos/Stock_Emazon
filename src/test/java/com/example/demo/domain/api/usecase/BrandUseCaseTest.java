package com.example.demo.domain.api.usecase;

import com.example.demo.domain.exception.EntityAlreadyExistsException;
import com.example.demo.domain.model.Brand;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.spi.IBrandPersistencePort;
import com.example.demo.domain.util.PaginationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)

class BrandUseCaseTest {
    private final IBrandPersistencePort brandPersistencePortMock = mock(IBrandPersistencePort.class, "brandPersistencePort");

    @Test
    void saveBrandWhenBrandPersistencePortBrandExistsByNameBrandGetBrandNameThrowsEntityAlreadyExistsException() {
        // Arrange Statement
        doReturn(true).when(brandPersistencePortMock).brandExistsByName("TechWorld");
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);
        Brand brand = new Brand();
        brand.setBrandName("TechWorld");

        // Act Statement
        EntityAlreadyExistsException result = assertThrows(EntityAlreadyExistsException.class, () -> target.saveBrand(brand));

        // Assert statement(s)
        assertAll("result",
                () -> assertThat(result, is(notNullValue())),
                () -> verify(brandPersistencePortMock).brandExistsByName("TechWorld")
        );
    }
    @Test
    void saveBrandWhenBrandPersistencePortNotBrandExistsByNameBrandGetBrandName() {

        // Arrange Statement
        doReturn(false).when(brandPersistencePortMock).brandExistsByName("TechWorld");
        Brand brand = new Brand();
        brand.setBrandName("TechWorld");
        doNothing().when(brandPersistencePortMock).saveBrand(brand);
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);

        // Act Statement
        target.saveBrand(brand);

        // Assert statement
        assertAll("result", () -> {
            verify(brandPersistencePortMock).brandExistsByName("TechWorld");
            verify(brandPersistencePortMock).saveBrand(brand);
        });
    }

    @Test
    void getAllBrandsPaginatedTest() {
        // Arrange Statement
        Pagination<Brand> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        doReturn(paginationMock).when(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock);
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);

        // Act Statement
        Pagination<Brand> result = target.getAllBrandsPaginated(paginationUtilMock);

        // Assert Statement
        assertAll("result", () -> {
            assertThat(result, equalTo(paginationMock));
            verify(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock);
        });
    }


}