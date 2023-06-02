package ru.internetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Monitor")
public class Monitor extends Product {

    @Column(name = "Diagonal")
    private int diagonal;
}
