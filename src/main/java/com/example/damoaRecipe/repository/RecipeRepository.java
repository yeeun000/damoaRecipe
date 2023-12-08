package com.example.damoaRecipe.repository;

import com.example.damoaRecipe.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Override
    ArrayList<Recipe> findAll(); //모든 레시피를 데이터베이스에서 조회



}
