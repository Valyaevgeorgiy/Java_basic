package com.example.clothessystemfx;

public class Constructors extends Product {
    private int numberOfParts;
    public Constructors(String name, double price, int quantity, String manufacturer, int numberOfParts) {
        super(name, price, quantity, manufacturer);
        this.numberOfParts = numberOfParts;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", количество тканевых материалов: " + numberOfParts;
    }
}