package ru.internetshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "PersonalComputer")
public class PersonalComputer extends Product {
    private String formFactor;
}
