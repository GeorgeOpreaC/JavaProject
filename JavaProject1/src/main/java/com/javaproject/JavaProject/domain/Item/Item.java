package com.javaproject.JavaProject.domain.Item;

import jakarta.persistence.*;

@Entity
@Table(name="Item", schema ="public" )
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int id;
    private String name;

    private String description;

    @Column(nullable = true)
    int userId;
    int inventoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }
}
