package com.example.clothessystemfx;

public class SoftClothes extends Product {

    private String material;
    public SoftClothes(String name, double price, int quantity, String manufacturer, String material) {
        super(name, price, quantity, manufacturer);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", материал: " + material;
    }
}
