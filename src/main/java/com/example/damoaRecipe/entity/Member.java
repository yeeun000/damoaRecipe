
package com.example.damoaRecipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private String memberId;
    @Column(name="member_pw")
    private String memberPw;
    @Column(name="member_name")
    private String memberName;
    @Column(name="member_email")
    private String memberEmail;
    @Column(name="member_image")
    private String memberImage;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="member_status")
    private Integer memberStatus;


    @OneToMany
    @JoinColumn(name="member_id")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberPw='" + memberPw + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberImage='" + memberImage + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", memberStatus=" + memberStatus +
                '}';
    }
}

