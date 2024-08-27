package org.clevertec.checkcrud.mapper;

import org.clevertec.checkcrud.dto.ProductDto;
import org.clevertec.checkcrud.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
