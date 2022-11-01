package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductDetail extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

/*
    @OneToMany(mappedBy = "product")
    private List<Photo> photo = new ArrayList<>();*/

    private String productName ;
    private String categoryName;
    private int pnt;
    private String content;
    private Long price;

    private String fileName;
    private String urlFileName;
    private String productStatus;
    private LocalDateTime endDt;
    private String writer;

/*    public void addPhoto(Photo photo){
        this.photo.add(photo);
        if(photo.getProduct()!= this){
            photo.setProduct(this);
        }
    }*/



}
