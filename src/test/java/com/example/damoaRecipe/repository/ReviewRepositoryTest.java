/*package com.example.damoaRecipe.repository;

import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.entity.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByRecipeId() {
        {
            Long recipeId=4L;
            List<Review> review=reviewRepository.findByRecipeId(recipeId);
            Recipe recipe=new Recipe(4L, "ㄹㅁㅇ","ㅂㄷ");
            Review a=new Review(1L, recipe,"asdf","adf");
            Review b=new Review(2L, recipe, "ad","a");
            Review c=new Review(3L,recipe,"asd","a");
            List<Review> expected= Arrays.asList(a,b,c);
            assertEquals(expected.toString(),review.toString(),"4번 글의 모든 댓글을 출력!");
        }
    }

    @Test
    void findByReviewAuthorId() {
    }

}*/