package org.clevertec.checkcrud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DiscountCard {

    private int id;
    private int number;
    private int discount;
}
