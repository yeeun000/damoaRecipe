package com.example.damoaRecipe.service;

import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.form.RecipeForm;
import com.example.damoaRecipe.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> index(){ //모든 레시피를 조회
        return recipeRepository.findAll(); //데이터베이스에서 모든 레시피를 가져옴
    }

    public Recipe show(Long recipeId) { //특정 레시피의 상세 정보를 조회
        return recipeRepository.findById(recipeId).orElse(null); //해당 ID의 레시피를 가져옴
    }

    public Recipe create(RecipeForm dto) {// 새로운 레시피를 생성
        Recipe recipe=dto.toEntity(); //레시피 엔터티로 변환
        if(recipe.getRecipeId() != null){
            return null;
        }
        return recipeRepository.save(recipe);
    }

    public Recipe update(Long recipeId, RecipeForm dto) { //특정 레시피를 수정
        Recipe recipe=dto.toEntity(); //레시피 엔터티로 변환
        log.info("id: {}, recipe: {}",recipeId, recipe.toString());
        Recipe target=recipeRepository.findById(recipeId).orElse(null); //해당 ID를 찾아 수정
        if(target==null || recipeId != recipe.getRecipeId()){ //찾은 레시피가 없거나 레시피ID랑 일치하지 않을 경우
            log.info("잘못된 요청! id: {}, recipe: {}",recipeId, recipe.toString());
            return null;
        }
        target.patch(recipe); //찾은 레시피를 수정
        Recipe updated=recipeRepository.save(target); //수정된 레시피를 저장하고 반환
        return updated;
    }

    public Recipe delete(Long recipeId) { //특정 레시피를 삭제
        Recipe target=recipeRepository.findById(recipeId).orElse(null); //레시피ID에 해당하는 레시피를 찾음
        if(target==null){ //찾은 레시피가 null일경우 null 반환
            return null;
        }
        recipeRepository.delete(target); //특정 레시피를 삭제
        return target;
    }
    @Transactional
    public List<Recipe> createRecipe(List<RecipeForm> dtos) { //여러 개의 레시피를 생성
        List<Recipe> recipeList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList()); //레시피를 레시피엔터티로 반환
        recipeList.stream().forEach(recipe -> recipeRepository.save(recipe)); //레시피 리스트를 데이터베이스에 저장
        recipeRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!")); //레시피 조회, 조회되지 않으면 예외 발생
        return recipeList;
    }
}
