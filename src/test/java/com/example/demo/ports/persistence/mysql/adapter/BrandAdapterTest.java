package com.example.demo.ports.persistence.mysql.adapter;

import com.example.demo.domain.model.Brand;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;
import com.example.demo.ports.persistence.mysql.entity.BrandEntity;
import com.example.demo.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.IBrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.mockito.MockedStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BrandAdapterTest {

    private final IBrandEntityMapper brandEntityMapperMock = mock(IBrandEntityMapper.class, "brandEntityMapper");

    private final IBrandRepository brandRepositoryMock = mock(IBrandRepository.class, "brandRepository");

    private final BrandEntity brandEntityMock = mock(BrandEntity.class);

    private final Page<BrandEntity> pageMock = mock(Page.class);

    private final PageRequest pageRequestMock = mock(PageRequest.class);

    private final Sort sortMock = mock(Sort.class);


    @Test
    void saveBrandTest() {
        // Arrange
        Brand brandMock = mock(Brand.class);
        BrandEntity brandEntityMockTest = mock(BrandEntity.class);

        doReturn(brandEntityMockTest).when(brandEntityMapperMock).toEntitybrand(brandMock);
        doReturn(brandEntityMockTest).when(brandRepositoryMock).save(brandEntityMockTest);

        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

        // Act
        target.saveBrand(brandMock);

        // Assert
        assertAll("result",
                () -> verify(brandEntityMapperMock).toEntitybrand(brandMock),
                () -> verify(brandRepositoryMock).save(brandEntityMockTest)
        );
    }


    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameIsPresent() {

        // (brandRepository.findByBrandName(brandName).isPresent()) : true

        //Arrange Statement
        doReturn(Optional.of(brandEntityMock)).when(brandRepositoryMock).findByBrandName("TechWorld");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

        //Act Statement
        boolean result = target.brandExistsByName("TechWorld");

        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.TRUE));
            verify(brandRepositoryMock).findByBrandName("TechWorld");
        });
    }

    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameNotIsPresent() {

        //(brandRepository.findByBrandName(brandName).isPresent()) : false

        //Arrange Statement
        doReturn(Optional.empty()).when(brandRepositoryMock).findByBrandName("TechWorld");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

        //Act Statement
        boolean result = target.brandExistsByName("TechWorld");

        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.FALSE));
            verify(brandRepositoryMock).findByBrandName("TechWorld");
        });
    }
    @Test
    void getAllBrandsPaginatedWhenPaginationUtilIsAscending() {
        // Arrange
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<BrandEntity> brandEntityList = Collections.emptyList();
            List<Brand> brandList = Collections.emptyList();

            when(brandRepositoryMock.findAll(pageRequestMock)).thenReturn(pageMock);
            when(pageMock.getContent()).thenReturn(brandEntityList);
            when(pageMock.getTotalPages()).thenReturn(0);
            when(pageMock.getTotalElements()).thenReturn(1L);
            when(brandEntityMapperMock.toBrandList(brandEntityList)).thenReturn(brandList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.DESC, "brandName")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationBrandUtil = new PaginationUtil();
            paginationBrandUtil.setPageNumber(0);
            paginationBrandUtil.setPageSize(0);
            paginationBrandUtil.setAscending(false);
            paginationBrandUtil.setNameFilter("brandName");

            BrandAdapter brandAdapter = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

            // Act
            Pagination<Brand> result = brandAdapter.getAllBrandsPaginated(paginationBrandUtil);

            // Assert
            assertThat(result.getContent(), is(brandList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(brandRepositoryMock).findAll(pageRequestMock);
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
            verify(pageMock).getTotalElements();
            verify(brandEntityMapperMock).toBrandList(brandEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.DESC, "brandName"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }




    @Test
    void getAllBrandsPaginatedWhenPaginationUtilNotIsAscending() {
        // Arrange
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<BrandEntity> brandEntityList = Collections.emptyList();
            List<Brand> brandList = Collections.emptyList();

            when(brandRepositoryMock.findAll(pageRequestMock)).thenReturn(pageMock);
            when(pageMock.getContent()).thenReturn(brandEntityList);
            when(pageMock.getTotalPages()).thenReturn(0);
            when(pageMock.getTotalElements()).thenReturn(1L);
            when(brandEntityMapperMock.toBrandList(brandEntityList)).thenReturn(brandList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.DESC, "brandName")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationBrandUtil = new PaginationUtil();
            paginationBrandUtil.setPageNumber(0);
            paginationBrandUtil.setPageSize(0);
            paginationBrandUtil.setAscending(false);
            paginationBrandUtil.setNameFilter("brandName");

            BrandAdapter brandAdapter = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

            // Act
          Pagination<Brand> result = brandAdapter.getAllBrandsPaginated(paginationBrandUtil);

            // Assert
            assertThat(result.getContent(), is(brandList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(brandRepositoryMock).findAll(pageRequestMock);
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
            verify(pageMock).getTotalElements();
            verify(brandEntityMapperMock).toBrandList(brandEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.DESC, "brandName"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }
}
