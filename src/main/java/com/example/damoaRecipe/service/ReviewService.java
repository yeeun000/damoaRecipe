package com.example.damoaRecipe.service;


import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.entity.Review;
import com.example.damoaRecipe.form.ReviewForm;
import com.example.damoaRecipe.repository.RecipeRepository;
import com.example.damoaRecipe.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    public ReviewForm create(Long recipeId, ReviewForm form) { //특정 레시피에 대한 리뷰를 생성
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패!"+"대상 게시글이 없습니다.")); //해당 ID 레시피를 찾음
        Review review=Review.createReview(form,recipe); //리뷰를 생성
        Review created=reviewRepository.save(review);//리뷰를 reviewRepository에 저장
        return ReviewForm.createReviewForm(created); //생성된 Review를 ReviewForm으로 변환하고 반환
    }

    public List<ReviewForm> review(Long recipeId) { //특정 레시피에 대한 리뷰를 조회
        return reviewRepository.findByRecipeId(recipeId).stream().map(review->ReviewForm.createReviewForm(review))
                .collect(Collectors.toList()); //특정 레시피 ID에 해당하는 리뷰 검색, 수집
    }

    @Transactional
    public ReviewForm update(Long reviewId, ReviewForm form) { //특정 리뷰를 수정
        Review target=reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패!"+"대상 댓글이 없습니다.")); //특정 리뷰ID에 해당하는 리뷰를 찾음
        target.patch(form); //리뷰를 ReviewForm으로 수정
        Review updated=reviewRepository.save(target); //리뷰를 데이터베이스에 저장
        return ReviewForm.createReviewForm(updated); //리뷰를 ReviewForm으로 변환하고 반환
    }

    @Transactional
    public ReviewForm delete(Long reviewId) {
        Review target=reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("댓글 삭제 시류ㅐ! 대상이 없습니다.")); //특정 리뷰ID에 해당하는 리뷰를 찾음
        reviewRepository.delete(target); //리뷰를 데이터베이스에서 삭제
        return ReviewForm.createReviewForm(target);// 삭제된 리뷰를 ReviewForm으로 변환하고 반환
    }
}