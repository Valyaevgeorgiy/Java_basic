package com.example.clothessystemfx;

public class Shop {
    private Product[] products;

    public Shop(int size) {
        products = new Product[size];
    }

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                System.out.println(product.getInfo());
                break;
            }
        }
    }

    public void changeQuantity(String name, int quantity) {
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                product.setQuantity(quantity);
            }
        }
    }

    public void printProductsInfo() {
        for (Product product : products) {
            if (product != null) {
                System.out.println(product.getInfo());
            }
        }
    }

    public Product[] getProducts() {
        return products;
    }
}