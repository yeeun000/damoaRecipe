package com.example.damoaRecipe.form;

import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReviewForm{
    private Long reviewId;//리뷰 아이디
    private Long recipeId;//레시피
    private String reviewAuthorId;//리뷰 작성자
    private String reviewContent;//리뷰 내용
    private Integer reviewRating;//리뷰 별점
    private LocalDateTime reviewCreatedAt;//리뷰 작성시간
    private LocalDateTime reviewUpdatedAt; //리뷰 수정시간
    public Integer reviewStatus; //리뷰 상태

    public static ReviewForm createReviewForm(Review review) {
        return new ReviewForm(
                review.getReviewId(), //댓글 엔티티의 reviewid
                review.getRecipe().getRecipeId(), //댓글 엔티티가 속한 부모 레시피의 recipeid
                review.getReviewAuthorId(), //댓글 엔티티의 reviewaithorid
                review.getReviewContent(), //댓글 엔티티의 reviewcontent
                review.getReviewRating(),
                review.getReviewCreatedAt(),
                review.getReviewUpdatedAt(),
                1);
    }
}