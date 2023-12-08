/*package com.example.damoaRecipe.entity;

import com.example.damoaRecipe.repository.RecipeRepository;
import com.example.damoaRecipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class RecipeTest {
    @Autowired
    RecipeService recipeService;
    @Autowired RecipeRepository recipeRepository;

    @Autowired
    public RecipeTest(RecipeService memberService, RecipeRepository memberRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @Test
    public void member_create() throws Exception {

        // Member member = new Member("0525", "이정민", "shinin_2008@naver.com", "룰루랄라");

        // Member member = new Member("0408", "김종현", "jjong0408@naver.com", "블링블링이즈종현");

        Recipe recipe = new Recipe(456, "이태민", "taem0718@naver.com");

        Recipe savedRecipe = recipeRepository.save(recipe);

        assertThat(savedRecipe.getRecipeName()).isEqualTo(recipe.getRecipeName());

    }

    @Test
    public void member_update() throws Exception {

        Recipe recipe = new Recipe(456, "이태민", "taem0718@naver.com");
        recipe.setUpdatedAt(LocalDateTime.now());
        recipeRepository.save(recipe);
    }

    @Test
    public void member_delete() throws Exception {
        Recipe member = new Recipe(456, "이태민", "taem0718@naver.com");
        Recipe savedMember = recipeRepository.save(member);
        System.out.println("memberId : " + savedMember.getRecipeId());
        recipeRepository.deleteById(savedMember.getRecipeId());
    }
}
*/

