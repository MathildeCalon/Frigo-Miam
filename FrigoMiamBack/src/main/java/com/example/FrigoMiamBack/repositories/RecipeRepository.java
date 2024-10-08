package com.example.FrigoMiamBack.repositories;

import com.example.FrigoMiamBack.entities.Account;
import com.example.FrigoMiamBack.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    //    List<Integer> findRecipeGrades(UUID id);
//    int findGradeByRecipeAndAccount(UUID recipeId, UUID accountId);

//    List<Recipe> findrecipeCreatedList(UUID accountId);
}
