package com.example.damoaRecipe.api;

import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.form.RecipeForm;
import com.example.damoaRecipe.repository.RecipeRepository;
import com.example.damoaRecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RecipeApiController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping("/api/recipe") //모든 레시피의 목록을 조회
    public List<Recipe> index(){
        return recipeService.index();
    }

    @GetMapping("/api/recipe/{recipeId}") //특정 ID 레시피를 조회
    public Recipe show(@PathVariable Long recipeId){
        return recipeService.show(recipeId);
    }

    @PostMapping("/api/recipe") //새로운 레시피를 생성
    public ResponseEntity<Recipe> create(@RequestBody RecipeForm dto){
        Recipe created=recipeService.create(dto);
        return (created!=null) ? ResponseEntity.status(HttpStatus.OK).body(created):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/recipe/{recipeId}") //특정 ID의 레시피를 수정
    public ResponseEntity<Recipe> update(@PathVariable Long recipeId, @RequestBody RecipeForm dto){
        Recipe updated=recipeService.update(recipeId,dto);
        return (updated != null)?ResponseEntity.status(HttpStatus.OK).body(updated):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/recipe/{recipeId}") //특정 ID 레시피를 삭제
    public ResponseEntity<Recipe> delete(@PathVariable Long recipeId){
        Recipe deleted =recipeService.delete(recipeId);// 주어진 레시피ID에 해당하는 레시피를 삭제
        return (deleted != null)?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //상태코드 응답
    }

    @PostMapping("/api/transaction-test") //테스트
    public ResponseEntity<List<Recipe>> transactionTest(@RequestBody List<RecipeForm> dtos){
        List<Recipe> createdList=recipeService.createRecipe(dtos);
        return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
