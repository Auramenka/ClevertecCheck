package org.clevertec.checkcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholesale;
}
