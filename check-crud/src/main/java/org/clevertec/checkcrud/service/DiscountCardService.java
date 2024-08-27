package org.clevertec.checkcrud.service;

import org.clevertec.checkcrud.dto.DiscountCardDto;

public interface DiscountCardService {
    DiscountCardDto saveDiscountCard(DiscountCardDto discountCardDto);
    void deleteDiscountCard(int id);
    DiscountCardDto updateDiscountCard(DiscountCardDto discountCardDto);
    DiscountCardDto getDiscountCardById(int id);
}
