package ru.internetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Monitor")
@NoArgsConstructor
public class Monitor extends Product {

    @Column(name = "Diagonal")
    private int diagonal;

    public Monitor(long id, String serialNumber, String company, int price, int quantity, int diagonal) {
        this.setId(id);
        this.setSerialNumber(serialNumber);
        this.setCompany(company);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.diagonal = diagonal;
    }
}
