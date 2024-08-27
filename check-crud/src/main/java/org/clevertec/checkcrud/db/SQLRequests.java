package org.clevertec.checkcrud.db;

public class SQLRequests {

    //product
    public static final String SAVE_PRODUCT =
            "insert into product (description, price, quantity_in_stock, wholesale_product) values (?, ?, ?, ?)";
    public static final String GET_PRODUCT_BY_ID = "select * from product where id = ?";
    public static final String DELETE_PRODUCT_BY_ID = "delete from product where id = ?";
    public static final String UPDATE_PRODUCT = "update product set description = ?, price = ?, " +
            "quantity_in_stock = ?, wholesale_product = ? where id = ?";

    //discountCard
    public static final String SAVE_DISCOUNT_CARD =
            "insert into discount_card (number, amount) values (?, ?)";
    public static final String GET_DISCOUNT_CARD_BY_ID = "select * from discount_card where id = ?";
    public static final String DELETE_DISCOUNT_CARD_BY_ID = "delete from discount_card where id = ?";
    public static final String UPDATE_DISCOUNT_CARD = "update discount_card set number = ?, amount = ? where id = ?";
}
