package ru.internetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "NoteBook")
public class NoteBook extends Product {
    @Column(name = "Size")
    private int size;
}
