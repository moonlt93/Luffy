package com.zerobase.luffy.member.user.entity;


import com.zerobase.luffy.member.admin.entity.ProductDetail;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderProId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;


    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private OrderItem orderItem;

    public OrderProduct(ProductDetail detail, OrderItem item){
        setOrderItem(item);
        setProductDetail(detail);
    }

    public void setOrderItem(OrderItem item){
        this.orderItem=item;
        item.getOrderProduct().add(this);

    }

    public void setProductDetail(ProductDetail detail){
        this.productDetail=detail;
        detail.getOrderProduct().add(this);
    }

    public void cancel(int pnt){
        productDetail.addPnt(pnt);
    }

}
