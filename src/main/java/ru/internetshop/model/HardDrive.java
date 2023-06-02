package ru.internetshop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HardDrive")
public class HardDrive extends Product {

    @Column(name = "Capacity")
    private int capacity;
}
