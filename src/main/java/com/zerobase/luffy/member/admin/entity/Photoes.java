package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="photoes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductDetail productDetail;


    private String writer;
    private String fileName;
    private String urlFileName;
    private String productName;

    public Photoes(ProductDetail productDetail){
        this.writer=productDetail.getWriter();
        this.fileName=productDetail.getFileName();
        this.urlFileName=productDetail.getUrlFileName();
        this.setProductDetail(productDetail);
    }


    public Photoes(Long id, String writer, String fileName, String urlFileName, String productName, long yourId) {

        this.id =id;
        this.urlFileName=urlFileName;
        this.writer=writer;
        this.fileName=fileName;
        this.productName =productName;
        this.setProductDetail(ProductDetail.builder()
                .id(yourId)
                .build());
    }

}

