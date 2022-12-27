package com.samsung.product_service.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String date;
    private int count;
    private int cost;

    public Product(String name, String date, int count, int cost) {
        this.name = name;
        this.date = date;
        this.count = count;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public int getCost() {
        return cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCost(int cost) {
        if (cost > 0) {
            this.cost = cost;
        }
    }

    public void updateInfo(Product product) {
        date = product.getDate();
        count += product.getCount();
        cost = product.getCost();
    }
}
