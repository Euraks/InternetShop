package ru.restfulapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "NoteBook")
@NoArgsConstructor
public class NoteBook extends Product {

    @Column(name = "Size")
    private int size;

    public NoteBook(long id, String serialNumber, String company, int price, int quantity, int size) {
        this.setId(id);
        this.setSerialNumber(serialNumber);
        this.setCompany(company);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.size = size;
    }
}
