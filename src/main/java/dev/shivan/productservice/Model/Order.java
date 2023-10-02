package dev.shivan.productservice.Model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Setter
@Getter
public class Order extends BaseModel{

    @ManyToMany
    @JoinTable(
        name = "product_orders",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
)
    private List<Product> product;
}
