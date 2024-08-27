package org.clevertec.checkcrud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Product {

    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholesale;
}
