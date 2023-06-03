package ru.internetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "PersonalComputer")
public class PersonalComputer extends Product {

    @Column(name = "Form-Factor")
    private String formFactor;
    public PersonalComputer(Long id,String serial, String company, int price, int quantity, String formFactor) {
        this.setId(id);
        this.setSerialNumber(serial);
        this.setCompany(company);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setFormFactor(formFactor);
    }

}
