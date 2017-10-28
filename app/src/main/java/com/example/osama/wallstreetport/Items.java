package com.example.osama.wallstreetport;

public abstract class Items {
    protected int numOwned;
    protected double price, originalPrice;
    protected double factor = 1.15;

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
/*
    public void setNumOwned(int numOwned) {
        this.numOwned = numOwned;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
    */
}