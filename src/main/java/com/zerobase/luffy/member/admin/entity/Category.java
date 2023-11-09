package com.zerobase.luffy.member.admin.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long categoryId;


    private String categoryName;
    private int sortValue;
    private boolean usingYn;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;


    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<ProductDetail> productDetails= new ArrayList<>();




}
