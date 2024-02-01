package com.ssafy.tosi.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer childId;

//    @Column(nullable = false)
//    @JoinColumn(name = "userId")
    private Integer userId;

    @Column(nullable = false)
    private String childName;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false)
    private int isMyBaby;

    @Builder
    public Child(Integer userId, String childName, int gender, int isMyBaby) {
        this.userId = userId;
        this.childName = childName;
        this.gender = gender;
        this.isMyBaby = isMyBaby;
    }

}
