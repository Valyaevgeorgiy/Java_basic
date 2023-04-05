package com.example.clothessystemfx;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String manufacturer;

    public Product(String name, double price, int quantity, String manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getInfo() {
        return "Название: " + name + ", цена: " + price + ", количество: " + quantity + ", производитель: " + manufacturer;
    }
}