package ru.internetshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonalComputer extends Product{
    private String formFactor;
}
