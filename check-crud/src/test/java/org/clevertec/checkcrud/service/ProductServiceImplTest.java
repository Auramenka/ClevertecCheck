package org.clevertec.checkcrud.service;

import org.clevertec.checkcrud.dto.ProductDto;
import org.clevertec.checkcrud.mapper.ProductMapper;
import org.clevertec.checkcrud.model.Product;
import org.clevertec.checkcrud.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    ProductServiceImpl testSubject;

    @Test
    void shouldSaveCurrentProduct() {
        //given
        ProductDto productDto = new ProductDto();
        productDto.setDescription("Milk");
        productDto.setPrice(2.10);
        productDto.setQuantity(1);
        productDto.setWholesale(true);

        Product product = new Product();
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.save(product)).thenReturn(product);
        //when
        ProductDto result = testSubject.saveProduct(productDto);
        //then
        assertNotNull(result);
        verify(productMapper, times(1)).toDto(product);
        verify(productMapper, times(1)).toEntity(productDto);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldNotSaveProduct_thenException() {
        //given
        ProductDto productDto = null;
        //when
        RuntimeException result = assertThrows(RuntimeException.class, () -> testSubject.saveProduct(productDto));
        //then
        assertEquals("ProductDto is empty", result.getMessage());
    }

    @Test
    void shouldDeleteExistedProduct() {
        //given
        int id = 100;
        Product product = new Product();
        product.setId(100);
        //when
        testSubject.deleteProduct(id);
        //then
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldUpdateExistedProduct() {
        //given
        ProductDto productDto = new ProductDto();

        Product product = new Product();
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.update(product)).thenReturn(product);
        //when
        ProductDto result = testSubject.updateProduct(productDto);
        //then
        assertNotNull(result);
        verify(productMapper, times(1)).toDto(product);
        verify(productMapper, times(1)).toEntity(productDto);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void shouldNotUpdateProductDtoIsEmpty_thenException() {
        //given
        ProductDto productDto = null;
        //when
        RuntimeException result = assertThrows(RuntimeException.class, () -> testSubject.updateProduct(productDto));
        //then
        assertEquals("ProductDto is empty", result.getMessage());
    }

    @Test
    void shouldGetProductById() {
        //given
        int id = 100;
        Product product = new Product();
        product.setId(id);

        ProductDto productDto = new ProductDto();
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.getById(id)).thenReturn(product);
        //when
        ProductDto result = testSubject.getProductById(id);
        //then
        assertNotNull(result);
        verify(productMapper, times(1)).toDto(product);
        verify(productRepository, times(1)).getById(id);
    }
}