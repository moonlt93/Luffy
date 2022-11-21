package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.repository.CouponRepository;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.type.OrderStatus;
import com.zerobase.luffy.member.type.PaymentStatus;
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
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    private final RedissonClient redissonClient;

    @Override
    public List<Coupon> getCoupons(Long id) {

        List<Coupon> coupons= couponRepository.findByMember_id(id);

        return coupons;
    }


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
            int minus = dto.getProductCnt();
             detail.minus(minus);

            //적립금 추가 및 제거
            Member members = optionalMember.get();

            Long re = members.getReserve();

            ReserveMaker(members,dto, re);


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

            pay.getOrderItem().add(order);

            //저장에 성공했다면 3초 락
            LockMyself(pay);

            return PaymentDto.of(pay);



        }
        return null;

    }



    void LockMyself(Payment pays) {

        RLock lock =redissonClient.getLock(pays.getUsername());

        try{
            boolean isLocked = lock.tryLock(3,3,TimeUnit.SECONDS);
            log.info("Lock Success");
            Thread.sleep(1000);
            if(!isLocked){
                log.info("Lock fail");
                throw new RuntimeException("락 획득에 실패했습니다.");
            }

            paymentRepository.save(pays);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            //락 해제
            log.info("UnLock success ");
            lock.unlock();
        }

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


     void ReserveMaker( Member members,PaymentDto dto, Long val){
        if(members.getReserve() == 0){
            members.setReserve(dto.getReservePay()+dto.getPlusReserve());
        }
        if(dto.getReservePay() == 0) {
            members.setReserve(val+dto.getPlusReserve());
        }

        members.setReserve(val-dto.getReservePay()+dto.getPlusReserve());
    }
}
