package com.yourbutler.fpv6.model;


public class CookingStep {
    private int stepId;
    private int recipeId;
    private int stepNumber;
    private String stepDescription;

    public CookingStep(int stepId, int recipeId, int stepNumber, String stepDescription) {
        this.stepId = stepId;
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
    }

    // Getters and setters
    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}