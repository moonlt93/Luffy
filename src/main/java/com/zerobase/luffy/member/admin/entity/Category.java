package com.zerobase.luffy.member.admin.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long categoryId;


    private String categoryName;
    private int sortValue;
    private boolean usingYn;


}
