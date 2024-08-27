package org.clevertec.checkcrud.repository;

import org.clevertec.checkcrud.model.DiscountCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardRepositoryImplTest {

    private final DiscountCardRepository discountCardRepository = new DiscountCardRepositoryImpl();

    @Test
    void testSave() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setNumber(2547);
        discountCard.setDiscount(10);

        DiscountCard savedDiscountCard = discountCardRepository.save(discountCard);

        assertNotNull(savedDiscountCard);
    }

    @Test
    void testDelete() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(9);

        discountCardRepository.deleteById(discountCard.getId());
    }

    @Test
    void testGetById() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(4);
        discountCard.setNumber(1457);
        discountCard.setDiscount(10);

        DiscountCard result = discountCardRepository.getById(discountCard.getId());

        assertNotNull(result);
        assertEquals(discountCard.getNumber(), result.getNumber());
        assertEquals(discountCard.getDiscount(), result.getDiscount());
    }

    @Test
    void testUpdate() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(5);
        discountCard.setNumber(2589);
        discountCard.setDiscount(7);

        discountCardRepository.update(discountCard);

        DiscountCard updatedDiscountCard = discountCardRepository.getById(discountCard.getId());

        assertNotNull(updatedDiscountCard);
        assertEquals(discountCard.getNumber(), updatedDiscountCard.getNumber());
        assertEquals(discountCard.getDiscount(), updatedDiscountCard.getDiscount());
    }
}