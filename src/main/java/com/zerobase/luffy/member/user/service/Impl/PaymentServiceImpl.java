package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.repository.CouponRepository;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.type.OrderStatus;
import com.zerobase.luffy.member.type.PaymentStatus;
import com.zerobase.luffy.member.type.PaymentType;
import com.zerobase.luffy.member.user.dto.PaymentDto;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.entity.Payment;
import com.zerobase.luffy.member.user.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ProductDetailRepository productDetailRepository;

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
                .orElseThrow(() -> new NullPointerException("해당하는 Order가 없습니다.")));
        Optional<Member> optionalMember = Optional.ofNullable( memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NullPointerException("해당하는 Member가 없습니다.")));

        Optional<ProductDetail> optionalProductDetail = Optional.ofNullable(productDetailRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NullPointerException("해당하는 Product가 없습니다")));


        if(optionalOrderItem.isPresent() || optionalMember.isPresent() || optionalProductDetail.isPresent()) {
            ProductDetail detail = optionalProductDetail.get();
            //수량 감소
            int currentQuantity = detail.getPnt();
            detail.setPnt(currentQuantity-dto.getProductCnt());

            //적립금 추가 및 제거
            Member members =optionalMember.get();
            if(members.getReserve() == null){
                members.setReserve(dto.getReservePay()+dto.getPlusReserve());
            }
            Long re = members.getReserve();
            members.setReserve(re-dto.getReservePay()+dto.getPlusReserve());

            OrderItem order = optionalOrderItem.get();
            order.setOrderStatus(OrderStatus.Costed);




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


            /*보조금 set*/
            Thread.sleep(5000);

            pay.getOrderItem().add(order);

            paymentRepository.save(pay);
            return PaymentDto.of(pay);
        }
        return null;



    }

    @Override
    public PaymentDto seletMyPayList(Long paymentId) {
       Optional<Payment> optionalPayment= Optional.ofNullable(paymentRepository.findById(paymentId)
               .orElseThrow(() -> new NullPointerException("해당하는 전표가 없습니다.")));
       if(optionalPayment.isPresent()){

           Payment pay = optionalPayment.get();

           PaymentDto paying = PaymentDto.of(pay);

           return paying;
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
