package com.example.damoaRecipe.api;

import com.example.damoaRecipe.form.ReviewForm;
import com.example.damoaRecipe.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewApiController{

    @Autowired
    private ReviewService reviewService;

    @GetMapping("api/recipe/{recipeId}/review") //특정 레시피에 대한 모든 리뷰를 조회
    public ResponseEntity<List<ReviewForm>> review(@PathVariable Long recipeId){
        List<ReviewForm> dtos=reviewService.review(recipeId); //조회된 리뷰 정보를 가저옴
        return ResponseEntity.status(HttpStatus.OK).body(dtos); //리뷰 정보와 상태 코드를 반환
    }

    @PostMapping("/api/recipe/{recipeId}/review") //특정 레시피에 새로운 리뷰를 생성
    public ResponseEntity<ReviewForm> create(@PathVariable Long recipeId, @RequestBody ReviewForm form){
        ReviewForm createForm=reviewService.create(recipeId, form); //생성된 리뷰 정보를 가져옴
        return ResponseEntity.status(HttpStatus.OK).body(createForm); //새로운 리뷰의 정보와 상태 코드를 반환
    }

    @PatchMapping("/api/review/{reviewId}") //특정 리뷰를 수정
    public ResponseEntity<ReviewForm> update(@PathVariable Long reviewId, @RequestBody ReviewForm form){
        ReviewForm updatedForm=reviewService.update(reviewId, form); //수정된 리뷰 정보를 가져옴
        return ResponseEntity.status(HttpStatus.OK).body(updatedForm); //수정된 리뷰의 정보와 상태 코드를 반환
    }

    @DeleteMapping("/api/review/{reviewId}") //특정 리뷰를 삭제
    public ResponseEntity<ReviewForm> delete(@PathVariable Long reviewId){
        ReviewForm deletedForm=reviewService.delete(reviewId); //삭제된 리뷰 정보를 가져옴
        return ResponseEntity.status(HttpStatus.OK).body(deletedForm); //삭제된 리뷰의 정보와 상태 코드를 반환
    }
}