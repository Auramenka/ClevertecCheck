package org.clevertec.checkcrud.service;

import lombok.AllArgsConstructor;
import org.clevertec.checkcrud.dto.DiscountCardDto;
import org.clevertec.checkcrud.mapper.DiscountCardMapper;
import org.clevertec.checkcrud.model.DiscountCard;
import org.clevertec.checkcrud.repository.DiscountCardRepository;

@AllArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardMapper discountCardMapper;
    private final DiscountCardRepository discountCardRepository;

    @Override
    public DiscountCardDto saveDiscountCard(DiscountCardDto discountCardDto) {
        checkDiscountCardDto(discountCardDto);
        DiscountCard discountCard = discountCardMapper.toEntity(discountCardDto);
        return discountCardMapper.toDto(discountCardRepository.save(discountCard));
    }

    @Override
    public void deleteDiscountCard(int id) {
        discountCardRepository.deleteById(id);
    }

    @Override
    public DiscountCardDto updateDiscountCard(DiscountCardDto discountCardDto) {
        checkDiscountCardDto(discountCardDto);
        DiscountCard discountCard = discountCardMapper.toEntity(discountCardDto);
        return discountCardMapper.toDto(discountCardRepository.update(discountCard));
    }

    @Override
    public DiscountCardDto getDiscountCardById(int id) {
        return discountCardMapper.toDto(discountCardRepository.getById(id));
    }

    private void checkDiscountCardDto(DiscountCardDto discountCardDto) {
        if (discountCardDto == null) {
            throw new RuntimeException("DiscountCardDto is empty");
        }
    }
}
