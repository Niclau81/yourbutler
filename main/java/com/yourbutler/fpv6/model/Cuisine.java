package com.yourbutler.fpv6.model;

public class Cuisine {
    private int cuisineId;
    private String cuisineName;

    public Cuisine(int cuisineId, String cuisineName) {
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "cuisineId=" + cuisineId +
                ", cuisineName='" + cuisineName + '\'' +
                '}';
    }
}
