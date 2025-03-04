package com.example.spring_la_mia_pizzeria_crud.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be null or blank, and its lenght cannot be less than 1")
    private String name;

    @Lob
    private String description;

    @NotBlank(message = "Allergens must not be null or blank, and its lenght cannot be less than 1")
    private String allergens;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0", inclusive = true, message = "Price must be at least 0.00")
    @Digits(integer = 6, fraction = 2, message = "Price must have at most 6 integer digits and 2 decimal places")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal price;

    // @NotNull(message = "Orders cannot be null")
    // @Min(value = 0, message = "Number of orders cannot be a negative")
    // private Integer orders;

    // Getter e Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllergens() {
        return this.allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // public Integer getOrders() {
    // return this.orders;
    // }

    // public void setOrders(Integer orders) {
    // this.orders = orders;
    // }

    @Override
    public String toString() {
        return String.format("Pizza: %s, Description: %s, Allergens: %s, Price: %2f€",
                this.name, this.description, this.allergens, this.price);
    }

}
