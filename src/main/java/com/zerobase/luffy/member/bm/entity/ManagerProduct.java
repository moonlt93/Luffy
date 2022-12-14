package com.zerobase.luffy.member.bm.entity;

import com.zerobase.luffy.Util.base.BaseHeader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ManagerProduct")
public class ManagerProduct extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Manager_product_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "ManagerProduct",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photoList = new ArrayList<>();

    private String productName ;
    private String categoryName;
    private int pnt;
    private String content;
    private Long price;
    private String companyName;

    private String fileName;
    private String urlFileName;
    private String productStatus;
    private LocalDateTime endDt;
    private String writer;


    public void addPhoto(final Photo photo){
        photoList.add(photo);
        photo.setManagerProduct(this);
    }


}
