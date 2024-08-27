package org.clevertec.checkcrud.service;

import lombok.AllArgsConstructor;
import org.clevertec.checkcrud.dto.ProductDto;
import org.clevertec.checkcrud.mapper.ProductMapper;
import org.clevertec.checkcrud.model.Product;
import org.clevertec.checkcrud.repository.ProductRepository;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        checkProductDto(productDto);
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        checkProductDto(productDto);
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.update(product));
    }

    @Override
    public ProductDto getProductById(int id) {
        return productMapper.toDto(productRepository.getById(id));
    }

    private void checkProductDto(ProductDto productDto) {
        if (productDto == null) {
            throw new RuntimeException("ProductDto is empty");
        }
    }
}
