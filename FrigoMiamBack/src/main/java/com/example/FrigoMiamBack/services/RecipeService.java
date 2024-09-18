package com.example.FrigoMiamBack.services;


import com.example.FrigoMiamBack.entities.Recipe;
import com.example.FrigoMiamBack.exceptions.ConflictException;
import com.example.FrigoMiamBack.exceptions.NotFoundException;
import com.example.FrigoMiamBack.exceptions.WrongParameterException;
import com.example.FrigoMiamBack.interfaces.IRecipeService;
import com.example.FrigoMiamBack.repositories.RecipeRepository;
import com.example.FrigoMiamBack.utils.constants.ExceptionsMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RecipeService implements IRecipeService {
    private final RecipeRepository recipeRepository;
    private final AccountService accountService;

    public RecipeService(RecipeRepository recipeRepository, AccountService accountService) {
        this.recipeRepository = recipeRepository;
        this.accountService = accountService;
    }

    @Override
    public Recipe findByID(UUID id) {
        if(id == null) {
            throw new WrongParameterException(ExceptionsMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }

        return this.recipeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(UUID id) {
        if(id == null) {
            throw new WrongParameterException(ExceptionsMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }

        return this.recipeRepository.existsById(id);
    }

    @Override
    public List<Recipe> findAll() {
        return this.recipeRepository.findAll();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if(recipe.getId() != null){
            throw new ConflictException(ExceptionsMessages.RECIPE_ALREADY_EXIST, HttpStatus.CONFLICT, LocalDateTime.now());
        }

        try {
            return this.recipeRepository.save(recipe);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        if(recipe.getId() == null){
            throw new WrongParameterException(ExceptionsMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        if(!this.recipeRepository.existsById(recipe.getId())){
            throw new NotFoundException(ExceptionsMessages.RECIPE_DOES_NOT_EXIST, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }

        try {
            return this.recipeRepository.save(recipe);
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public boolean deleteRecipe(UUID id) {
        System.out.println("service" + id);
        if(id == null){
            throw new WrongParameterException(ExceptionsMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        if(!this.recipeRepository.existsById(id)){
            throw new NotFoundException(ExceptionsMessages.RECIPE_DOES_NOT_EXIST, HttpStatus.NOT_FOUND, LocalDateTime.now());
        }

        try {
            this.recipeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Recipe> getFavoriteRecipes(String accountId) {
        return accountService.getAccountById(accountId).getRecipeLikedList();
    }

    @Override
    public List<Recipe> getRecipesByFilters(List<Ingredient> ingredients, List<Allergy> allergies, Diet diets) {
        List<Recipe> finalRecipes = this.recipeRepository.findAll();

        finalRecipes.forEach(recipe -> {

        });
        if(diets != null){
            finalRecipes = finalRecipes.stream().filter(recipe -> recipe.getDiet() == diets).toList();
        }
        //TODO AJOUTER UN INGREDIENT A UNE RECETTE POUR POUVOIR FILTRER/INGREDIENt et /ALLERGEN
//        if(ingredients != null){
//            for(Ingredient ingredient : ingredients){
//                for(Recipe recipe : finalRecipes){
//                    List<Recipe_Ingredient> recipeIngredients = recipe.getRecipeIngredientsList();
//                    System.out.println(recipeIngredients);
//                }
//                finalRecipes.stream().filter(recipe -> recipe.getRecipeIngredientsList())
//            }
//        }
        return finalRecipes;
    }

//
//    @Override
//    public int getAverageGrade(String recipeId) {
//        UUID id = UUID.fromString(recipeId);
//        try {
//            List<Integer> grades = this.recipeRepository.findRecipeGrades(id);
//            int count = 0;
//            for(int grade : grades) {
//                count += grade;
//            }
//            return count / grades.size();
//        } catch (Exception e) {
//            //TODO exception personnalisée
//            return 0;
//        }
//    }
//
//    @Override
//    public int getAccountGrade(String recipeId, String accountId) {
//        try {
//            return this.recipeRepository.findGradeByRecipeAndAccount(UUID.fromString(recipeId), UUID.fromString(accountId));
//        } catch (Exception e) {
//            //TODO exception
//            return 0;
//        }
//    }
//
//    @Override
//    public boolean addGradeToRecipe(String recipeId, String accountId, int grade) {
//        return this.recipeRepository.addGradeToRecipe(UUID.fromString(recipeId), UUID.fromString(accountId), grade);
//    }
//
//
//
//
//
//    @Override
//    public List<Recipe> getFavoriteRecipes(String accountId) {
//        try {
//            return this.recipeRepository.findRecipeLikedListByAccountId(UUID.fromString(accountId));
//        } catch (Exception e) {
//            //TODO exception
//            return null;
//        }
//    }
//
//    @Override
//    public List<Recipe> getRecipeCreated(String accountId) {
//        return this.recipeRepository.findrecipeCreatedList(UUID.fromString(accountId));
//    }
}
