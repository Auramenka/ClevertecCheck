package org.clevertec.checkcrud.mapper;

import org.clevertec.checkcrud.dto.DiscountCardDto;
import org.clevertec.checkcrud.model.DiscountCard;
import org.mapstruct.Mapper;

@Mapper
public interface DiscountCardMapper {
    DiscountCardDto toDto(DiscountCard discountCard);
    DiscountCard toEntity(DiscountCardDto discountCardDto);
}
