package com.example.spring_la_mia_pizzeria_crud.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
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

    @NotBlank
    private String allergens;

    @NotNull
    private Integer numberOfOrders;

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

    public Integer getNumberOfOrders() {
        return this.numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @Override
    public String toString() {
        return String.format("Pizza: %s, Description: %s, Allergens: %s, Number of Orders: %d",
                this.name, this.description, this.allergens, this.numberOfOrders);
    }

}
