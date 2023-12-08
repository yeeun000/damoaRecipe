package com.example.damoaRecipe.repository;

import com.example.damoaRecipe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value="SELECT * FROM review WHERE recipe_id= :recipeId",nativeQuery=true)
    List<Review> findByRecipeId(Long recipeId); //레시피 ID를 조회
    List<Review> findByReviewAuthorId(String reviewAuthorId); //리뷰 작성자ID를 조회



}