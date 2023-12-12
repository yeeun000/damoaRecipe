package com.example.damoaRecipe.entity;

import com.example.damoaRecipe.form.ReviewForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe; // 레시피

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId; // 리뷰 아이디

    @Column(name = "review_author_id")
    private String reviewAuthorId; // 리뷰 작성자

    @Column(name = "review_content")
    private String reviewContent; // 리뷰 내용

    @Column(name = "review_rating")
    private Integer reviewRating; // 리뷰 별점

    @CreationTimestamp
    @Column(name = "review_created_at")
    private LocalDateTime reviewCreatedAt; // 리뷰 작성시간

    @UpdateTimestamp
    @Column(name = "review_updated_at")
    private LocalDateTime reviewUpdatedAt; // 리뷰 수정시간

    @Column(name = "review_status")
    private Integer reviewStatus; // 리뷰 상태

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }



    public static Review createReview(ReviewForm form, Recipe recipe, Member member) {
        if (form.getReviewId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        }
        if (!form.getRecipeId().equals(recipe.getRecipeId())) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        return new Review(
                member,
                recipe,
                form.getReviewId(),
                form.getReviewAuthorId(),
                form.getReviewContent(),
                form.getReviewRating(),
                form.getReviewCreatedAt(),
                form.getReviewUpdatedAt(),
                form.getReviewStatus());
    }

    public void patch(ReviewForm form) {// 수정할 내용이 있는 경우 값을 갱신
        if (!this.reviewId.equals(form.getReviewId())) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        }
        if (form.getReviewAuthorId() != null) {
            this.reviewAuthorId = form.getReviewAuthorId();
        }
        if (form.getReviewContent() != null) {
            this.reviewContent = form.getReviewContent();
        }
        if (form.getReviewRating() != null) {
            this.reviewRating = form.getReviewRating();
        }
    }
}
