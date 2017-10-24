package com.example.osama.wallstreetport;

public abstract class Items {
    int numOwned;
    double price, originalPrice;

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
        price = (originalPrice * (Math.pow(1.15, numOwned)));
    }
}