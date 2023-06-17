package ru.restfulapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HardDrive")
@NoArgsConstructor
@AllArgsConstructor
public class HardDrive extends Product {

    @Column(name = "Capacity")
    private int capacity;

    public HardDrive(long id, String serialNumber, String company, int price, int quantity, int capacity) {
        this.setId(id);
        this.setSerialNumber(serialNumber);
        this.setCompany(company);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.capacity=capacity;
    }
}
