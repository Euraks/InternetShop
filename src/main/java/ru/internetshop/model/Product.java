package ru.internetshop.model;

import lombok.Data;

@Data
public abstract class Product {
    private int serialNumber;
    private String company;
    private int price;
    private int quantity;
}
