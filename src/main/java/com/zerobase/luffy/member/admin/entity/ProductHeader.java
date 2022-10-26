package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.member.type.MemberCode;
import com.zerobase.luffy.member.type.ProductCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ProductHeader")
public class ProductHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  /*  @OneToMany(mappedBy = "ProductHeader")
    @Column(name="id")
    private final List<ProductDetail> detail = new ArrayList<>();*/

    private String productName;

    private int pnt;
    @Enumerated(EnumType.STRING)
    private ProductCode memberStatus;


}
