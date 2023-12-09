package com.example.damoaRecipe.repository;

import com.example.damoaRecipe.entity.Recipe;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Override
    ArrayList<Recipe> findAll(); //모든 레시피를 데이터베이스에서 조회
   // List<Recipe> findAllByOrderByRecipeLikeDesc(Pageable pageable);
    //List<Recipe> findByCategoryId(Long categoryId, Pageable pageable);
}
