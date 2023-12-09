package com.example.damoaRecipe.form;

//import com.example.damoaRecipe.entity.Category;
import com.example.damoaRecipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class RecipeForm { //폼 데이터를 받아올 그릇
  //  private Long categoryId;
    private Long recipeId;//레시피 아이디
    private String recipeAuthorId;//레시피 작성자
    private String recipeName;//레시피 제목
    private String recipeContent;//레시피 내용
    private String recipeCalorie;//음식 칼로리
    private String recipeCookingTime;//음식 만드는 시간
    private Integer recipeLevel;//레시피 난이도
    private Integer recipeLike;//레시피 좋아요 수
    private LocalDateTime recipeCreatedAt;//레시피 생성 시간
    private LocalDateTime recipeUpdatedAt;//레시피 업데이트 시간
    private Integer recipeStatus;//레시피 상태

    public Recipe toEntity() { //엔터티 객체로 변환
        //Category category = new Category();
        //category.setCategoryId(categoryId);
        return new Recipe(
               // category,
                recipeId,
                "1",
                recipeName,
                recipeContent,
                recipeCalorie,
                recipeCookingTime,
                recipeLevel,
                0,
                recipeCreatedAt,
                recipeUpdatedAt,
                1);
    }


}
