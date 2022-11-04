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
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Manager_product_id")
    private ManagerProduct ManagerProduct;


    private String writer;
    private String fileName;
    private String urlFileName;

    public Photo(ManagerProduct managerProduct){
        this.writer=managerProduct.getWriter();
        this.fileName=managerProduct.getFileName();
        this.urlFileName=managerProduct.getUrlFileName();
        this.setManagerProduct(managerProduct);
    }



}

