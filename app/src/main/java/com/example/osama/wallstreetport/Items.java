package com.example.osama.wallstreetport;

public abstract class Items {
    int numOwned;
    double price, originalPrice;
    double factor = 1.15;

    public void getItems() {
        numOwned++;
        changePrice();
    }

    public int getNumOwned() {
        return numOwned;
    }

    public double getPrice() {
        return price;
    }

    public void changePrice() {
        price = (originalPrice * (Math.pow(factor, numOwned)));
    }
}