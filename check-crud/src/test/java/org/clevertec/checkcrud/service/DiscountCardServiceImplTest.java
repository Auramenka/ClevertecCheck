package org.clevertec.checkcrud.service;

import org.clevertec.checkcrud.dto.DiscountCardDto;
import org.clevertec.checkcrud.mapper.DiscountCardMapper;
import org.clevertec.checkcrud.model.DiscountCard;
import org.clevertec.checkcrud.repository.DiscountCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @Mock
    DiscountCardRepository discountCardRepository;

    @Mock
    DiscountCardMapper discountCardMapper;

    @InjectMocks
    DiscountCardServiceImpl testSubject;

    @Captor
    ArgumentCaptor<DiscountCard> discountCardArgumentCaptor;

    @Test
    void shouldSaveDiscountCard() {
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.setNumber(1846);
        discountCardDto.setDiscount(10);

        DiscountCard discountCard = new DiscountCard();
        discountCard.setNumber(1846);
        discountCard.setDiscount(10);
        when(discountCardMapper.toEntity(discountCardDto)).thenReturn(discountCard);
        when(discountCardMapper.toDto(discountCard)).thenReturn(discountCardDto);
        when(discountCardRepository.save(discountCard)).thenReturn(discountCard);

        testSubject.saveDiscountCard(discountCardDto);

        verify(discountCardMapper, times(1)).toDto(discountCard);
        verify(discountCardMapper, times(1)).toEntity(discountCardDto);
        verify(discountCardRepository).save(discountCardArgumentCaptor.capture());

        assertThat(discountCardArgumentCaptor.getValue().getNumber()).isEqualTo(1846);
        assertThat(discountCardArgumentCaptor.getValue().getDiscount()).isEqualTo(10);
    }

    @Test
    void shouldNotSaveDiscountCard_thenException() {
        //given
        DiscountCardDto discountCardDto = null;
        //when
        RuntimeException result = assertThrows(RuntimeException.class, () -> testSubject.saveDiscountCard(discountCardDto));
        //then
        assertEquals("DiscountCardDto is empty", result.getMessage());
    }

    @Test
    void shouldDeleteExistedDiscountCard() {
        //given
        int id = 100;
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(100);
        //when
        testSubject.deleteDiscountCard(id);
        //then
        verify(discountCardRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldUpdateExistedDiscountCard() {
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.setId(4);
        discountCardDto.setNumber(1557);
        discountCardDto.setDiscount(10);

        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(4);
        discountCard.setNumber(1557);
        discountCard.setDiscount(10);

        when(discountCardMapper.toEntity(discountCardDto)).thenReturn(discountCard);
        when(discountCardMapper.toDto(discountCard)).thenReturn(discountCardDto);
        when(discountCardRepository.update(discountCard)).thenReturn(discountCard);

        testSubject.updateDiscountCard(discountCardDto);

        verify(discountCardMapper, times(1)).toDto(discountCard);
        verify(discountCardMapper, times(1)).toEntity(discountCardDto);
        verify(discountCardRepository).update(discountCardArgumentCaptor.capture());

        assertThat(discountCardArgumentCaptor.getValue().getNumber()).isEqualTo(1557);
        assertThat(discountCardArgumentCaptor.getValue().getDiscount()).isEqualTo(10);
    }

    @Test
    void shouldNotUpdateDiscountCardDtoIsEmpty_thenException() {
        //given
        DiscountCardDto discountCardDto = null;
        //when
        RuntimeException result = assertThrows(RuntimeException.class, () -> testSubject.updateDiscountCard(discountCardDto));
        //then
        assertEquals("DiscountCardDto is empty", result.getMessage());
    }

    @Test
    void shouldGetDiscountCardById() {
        //given
        int id = 100;
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(id);

        DiscountCardDto discountCardDto = new DiscountCardDto();
        when(discountCardMapper.toDto(discountCard)).thenReturn(discountCardDto);
        when(discountCardRepository.getById(id)).thenReturn(discountCard);
        //when
        DiscountCardDto result = testSubject.getDiscountCardById(id);
        //then
        assertNotNull(result);
        verify(discountCardMapper, times(1)).toDto(discountCard);
        verify(discountCardRepository, times(1)).getById(id);
    }
}