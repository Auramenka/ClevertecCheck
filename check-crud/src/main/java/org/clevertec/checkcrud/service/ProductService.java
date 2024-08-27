package org.clevertec.checkcrud.service;


import org.clevertec.checkcrud.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    void deleteProduct(int id);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto getProductById(int id);
}
