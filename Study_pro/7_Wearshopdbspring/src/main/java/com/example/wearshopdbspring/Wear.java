package com.example.wearshopdbspring;

import javax.persistence.*;

// @Data
@Entity
@Table(name = "wear")
public class Wear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String size;

    private String public_date;

    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPublicDate() {
        return public_date;
    }

    public void setPublicDate(String public_date) {
        this.public_date = public_date;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}