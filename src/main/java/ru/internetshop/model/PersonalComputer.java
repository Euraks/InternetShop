package ru.internetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "PersonalComputer")
public class PersonalComputer extends Product {

    @Column(name = "Form-Factor")
    private String formFactor;
}
