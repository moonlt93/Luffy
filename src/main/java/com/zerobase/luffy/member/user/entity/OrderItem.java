package com.zerobase.luffy.member.user.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import com.zerobase.luffy.member.admin.entity.Category;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.type.OrderStatus;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;


    private String orderCode;
    private String productSize;
    private String productColor;
    private Long totalPrice;
    private Long reserve;
    private Long tax;
    private int count;

    @CreatedDate
    private LocalDateTime regDt;
    @UpdateTimestamp
    private LocalDateTime upDt;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    private String memberIp;
    private String name;
    private String username;
    private String phone;
    private String registration;

    //회사
    private String companyName;

    //제품
    @OneToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    private Long productId;
    private String writer;
    private String productName;


    public OrderItem(Member member) {
        this.memberIp = member.getIp();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.registration = member.getRegistration();
    }

    public OrderItem(ProductDetail detail){
        this.productId=detail.getId();
        this.writer=detail.getWriter();
        this.productName=detail.getProductName();
    }


}

