package com.hmmrfll.service;

import com.hmmrfll.model.Recipe;
import com.hmmrfll.model.User;

import java.util.List;

public interface RecipeService {
    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws Exception;
    public void deleteRecipe(Long id) throws Exception;
    public Recipe updeteRecipe(Recipe recipe, Long id) throws Exception;
    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
