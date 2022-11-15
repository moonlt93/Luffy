package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.repository.CouponRepository;
import com.zerobase.luffy.member.type.PaymentStatus;
import com.zerobase.luffy.member.type.PaymentType;
import com.zerobase.luffy.member.user.dto.PaymentDto;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.entity.Payment;
import com.zerobase.luffy.member.user.repository.OrderRepository;
import com.zerobase.luffy.member.user.repository.PaymentRepository;
import com.zerobase.luffy.member.user.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentServiceImpl implements PaymentService {


    private final CouponRepository couponRepository;

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Coupon> getCoupons(Long id) {

        List<Coupon> coupons= couponRepository.findByMember_id(id);

        return coupons;
    }

    @Transactional
    @Override
    public PaymentDto addPayment(PaymentDto dto) throws InterruptedException {
        Long id = dto.getOrderId();
        Optional<OrderItem> optionalOrderItem = Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Order가 없습니다.")));


        if(optionalOrderItem.isPresent()) {
            OrderItem order = optionalOrderItem.get();


            Payment pay = Payment.builder()
                    .paymentStatus(PaymentStatus.PAYMENT_DONE)
                    .paymentType(dto.getPaymentType())
                    .paymentId(IdMaker())
                    .tax(dto.getTax())
                    .productComment(dto.getProductComment())
                    .orderItem(new ArrayList<>())
                    .totalPrice(dto.getTotalPrice())
                    .username(dto.getUsername())
                    .build();

            order.setPayment(pay);
            /* 상품 갯수조절  */
            int sellTotal = order.getProductDetail().getPnt();
            int finalCount = sellTotal - order.getCount();
            order.getProductDetail().setPnt(finalCount);

            /*보조금 set*/
            Thread.sleep(5000);

            pay.getOrderItem().add(order);

            paymentRepository.save(pay);
            return PaymentDto.of(pay);
        }
        return null;



    }



    private Long IdMaker(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String dateLength=date.substring(0,4);
        int length = (int)((Math.random()+1)*10000);
        return Long.valueOf(dateLength+length);
    }
}
