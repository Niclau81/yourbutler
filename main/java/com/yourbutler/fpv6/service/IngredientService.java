package com.yourbutler.fpv6.service;

import com.yourbutler.fpv6.dao.IngredientDAO;
import com.yourbutler.fpv6.model.Ingredient;

import java.util.List;

public class IngredientService {
    private IngredientDAO ingredientDAO = new IngredientDAO();

    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.getAllIngredients();
    }
}
