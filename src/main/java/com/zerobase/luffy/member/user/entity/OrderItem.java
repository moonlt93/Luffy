package com.zerobase.luffy.member.user.entity;

import com.zerobase.luffy.Util.base.BaseHeader;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.type.OrderStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class OrderItem extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;


    private String orderCode;
    private String productSize;
    private String productColor;
    private Long price;
    private Long reserve;
    private Long tax;
    private int count;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paymentId")
    private Payment payment;

    private String memberIp;
    private String name;
    private String username;
    private String phone;
    private String registration;

    //회사
    private String companyName;

    // 상품 1개에 주문 여러개일수도?
    @OneToMany(mappedBy = "orderItem",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderProduct> orderProduct= new ArrayList<>();



    private Long productId;
    private String writer;
    private String productName;


    public OrderItem(Member member) {
        this.memberIp = member.getIp();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.registration = member.getRegistration();
    }




}

