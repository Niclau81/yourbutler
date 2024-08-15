package com.yourbutler.fpv6.model;
import java.util.List;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private int cuisineId;
    private String cookingTime;
    private String photo;  // Changed from photoPath to photo
    private List<CookingStep> cookingSteps;

    public Recipe(int recipeId, String recipeName, int cuisineId, String cookingTime, String photo) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.cuisineId = cuisineId;
        this.cookingTime = cookingTime;
        this.photo = photo;
    }

    // Getters and setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<CookingStep> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(List<CookingStep> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }
}