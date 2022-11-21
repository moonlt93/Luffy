package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.Util.base.BaseHeader;
import com.zerobase.luffy.member.type.BatchStatus;
import com.zerobase.luffy.member.user.entity.OrderProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;
    @Builder.Default
    @OneToMany(mappedBy = "productDetail",orphanRemoval = true)
    private List<Photoes> photoes =new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "productDetail",cascade = CascadeType.ALL)
    private List<OrderProduct> orderProduct= new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;

    private String productName;
    private String categoryName;
    private int pnt;
    private String content;
    private Long price;

    private String fileName;
    private String urlFileName;
    private String productStatus;
    private LocalDateTime endDt;
    private String writer;
    private String companyName;


    public ProductDetail(Long id, String productName, String productStatus, int pnt, Long price, String fileName, String urlFileName, String writer, LocalDateTime regDt, String categoryName, String content, String companyName) {
        this.id= id;
        this.pnt+= pnt;
        this.fileName = fileName;
        this.urlFileName = urlFileName;
        this.productStatus = String.valueOf(BatchStatus.BatchProduct);
        this.productName=productName;
        this.price=price;
        this.categoryName=categoryName;
        this.content=content;
        this.writer=writer;
        this.companyName = companyName ;
    }


    public void addPhotoes(final Photoes photo){
        photoes.add(photo);
        photo.setProductDetail(this);
    }
    public void addPnt(final int pnt){
        this.pnt+=pnt;
    }

    public void minus(final int num){
        this.pnt-= num;
    }




}
