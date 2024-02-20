package com.hmmrfll.controller;

import com.hmmrfll.model.Recipe;
import com.hmmrfll.model.User;
import com.hmmrfll.repository.UserRepository;
import com.hmmrfll.service.RecipeService;
import com.hmmrfll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe,
                               @RequestHeader("Authorization") String authHeader) throws Exception {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        Optional<User> findUser = userService.findUserByJwt(token);
        User user = findUser.orElse(null);
        return recipeService.createRecipe(recipe, user);
    }

    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception {
        return recipeService.findAllRecipe();
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "recipe delete successfully";

    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        return recipeService.updeteRecipe(recipe, id);
    }

    @PutMapping("/{recipeId}/like")
    public Recipe likeRecipe(@PathVariable Long recipeId,
                             @RequestHeader("Authorization") String authHeader) throws Exception {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        Optional<User> findUser = userService.findUserByJwt(token);
        User user = findUser.orElse(null);
        return recipeService.likeRecipe(recipeId, user);
    }


}
