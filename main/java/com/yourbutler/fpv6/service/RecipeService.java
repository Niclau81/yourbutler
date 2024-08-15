package com.yourbutler.fpv6.service;

import com.yourbutler.fpv6.dao.RecipeDAO;
import com.yourbutler.fpv6.model.Cuisine;
import com.yourbutler.fpv6.model.Recipe;

import java.util.List;

public class RecipeService {
    private RecipeDAO recipeDAO = new RecipeDAO();

    public List<Recipe> searchRecipesByIngredientsAndCuisine(List<Integer> ingredientIds, Integer cuisineId) {
        try {
            System.out.println("RecipeService: Searching recipes with ingredients: " + ingredientIds + ", cuisine: " + cuisineId);
            List<Recipe> recipes = recipeDAO.searchRecipesByIngredientsAndCuisine(ingredientIds, cuisineId);
            System.out.println("RecipeService: Found " + recipes.size() + " recipes");
            return recipes;
        } catch (Exception e) {
            System.err.println("Error in RecipeService.searchRecipesByIngredientsAndCuisine: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error searching for recipes", e);
        }
    }

    public List<Cuisine> getAllCuisines() {
        try {
            System.out.println("RecipeService: Fetching all cuisines");
            List<Cuisine> cuisines = recipeDAO.getAllCuisines();
            System.out.println("RecipeService: Found " + cuisines.size() + " cuisines");
            return cuisines;
        } catch (Exception e) {
            System.err.println("Error in RecipeService.getAllCuisines: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error fetching cuisines", e);
        }
    }
}