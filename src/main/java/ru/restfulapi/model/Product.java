package ru.restfulapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Product {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Serial_Number")
    private String serialNumber;
    @Column(name = "Company")
    private String company;
    @Column(name = "Price")
    private int price;
    @Column(name = "Quantity")
    private int quantity;
}
