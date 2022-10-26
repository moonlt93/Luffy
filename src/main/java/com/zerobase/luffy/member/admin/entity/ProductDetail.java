package com.zerobase.luffy.member.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name="id")
    private ProductHeader header;*/

    private String imgLink;
    private String content;
    private String productSize;
    private Long price;

    private LocalDateTime productRegDt;
    private LocalDateTime UpDt;
    private LocalDateTime EndDt;

/*    public void setProductHeader(ProductHeader header){

        this.header = header;

        if(!header.getDetail().contains(this)){
            header.getDetail().add(this);
        }
    }*/



}
