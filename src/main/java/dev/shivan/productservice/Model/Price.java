package dev.shivan.productservice.Model;

import jakarta.persistence.Entity;

@Entity
public class Price extends BaseModel{
    String currency;
    double price;
}
