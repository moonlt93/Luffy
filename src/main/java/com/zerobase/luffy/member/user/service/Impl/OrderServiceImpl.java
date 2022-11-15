package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.user.dto.OrderDto;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import com.zerobase.luffy.member.user.repository.OrderRepository;
import com.zerobase.luffy.member.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.zerobase.luffy.member.type.OrderStatus.PreCost;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductDetailRepository productDetailRepository;
    @Override
    public Object createOrder(OrderDto dto) {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String id = uuid + date;

            Optional<OrderItem> optionalOrderItem =orderRepository.findByOrderCode(dto.getOrderCode());


            if(optionalOrderItem.isEmpty()) {
                Optional<Member> optionalMember = Optional.ofNullable(memberRepository.findByUsername(dto.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("해당하는 username이 없습니다.")));

                Optional<ProductDetail> optionalProductDetail = Optional.ofNullable(productDetailRepository.findById(dto.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("잘못된 제품번호 입니다.")));
                Member members = optionalMember.get();
                ProductDetail productDetail = optionalProductDetail.get();


                OrderItem order = OrderItem.builder()
                        .orderId(dto.getOrderId())
                        .orderCode(id)
                        .orderStatus(PreCost)
                        .categoryName(dto.getCategoryName())
                        .companyName(dto.getCompanyName())
                        .productSize(dto.getProductSize())
                        .productColor(dto.getProductColor())
                        .productName(dto.getProductName())
                        .productColor(dto.getProductColor())
                        .tax(dto.getReserve())
                        .reserve(dto.getReserve())
                        .price(dto.getPrice())
                        .productId(dto.getProductId())
                        .registration(members.getRegistration())
                        .memberIp(members.getIp())
                        .name(members.getName())
                        .phone(members.getPhone())
                        .writer(productDetail.getWriter())
                        .productDetail(productDetail)
                        .member(members)
                        .username(members.getUsername())
                        .productId(dto.getProductId())
                        .count(dto.getCount())
                        .build();



                orderRepository.save(order);
                return order.getOrderId();
            }
            return null;
    }


    @Override
    public List<OrderItem> findByUserName(String name) {

        List<OrderItem> list  = orderRepository.findByUsername(name);

        return list;
    }

    @Override
    public OrderDto findByOrderCode(String code) {

        Optional<OrderItem> dto = Optional.ofNullable(orderRepository.findByOrderCode(code)
                .orElseThrow(() -> new RuntimeException("orderCode에 해당하는 주문번호를 찾을수 없습니다.")));

        if(dto.isPresent()){

            OrderItem order = dto.get();

            return OrderDto.builder()
                    .OrderId(order.getOrderId())
                    .CategoryName(order.getCategoryName())
                    .companyName(order.getCompanyName())
                    .productColor(order.getProductColor())
                    .productSize(order.getProductSize())
                    .price(order.getPrice())
                    .count(order.getCount())
                    .productName(order.getProductName())
                    .comment(Comment(order))
                    .build();
        }

        return null;
    }

    @Override
    public OrderDto findByOrderId(String code) {
        Long id = Long.valueOf(code);
        Optional<OrderItem> dto2 = Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("orderCode에 해당하는 주문번호를 찾을수 없습니다.")));

        if(dto2.isPresent()){

            OrderItem order = dto2.get();

            return OrderDto.builder()
                    .OrderId(order.getOrderId())
                    .CategoryName(order.getCategoryName())
                    .companyName(order.getCompanyName())
                    .productColor(order.getProductColor())
                    .productSize(order.getProductSize())
                    .price(order.getPrice())
                    .count(order.getCount())
                    .productName(order.getProductName())
                    .comment(Comment(order))
                    .build();
        }


        return null;
    }

    private String Comment(OrderItem order){
        StringBuilder sb = new StringBuilder();
        return   sb.append("["+order.getCategoryName()+"] : ").append(order.getProductName()+" ").append("\n")
                .append(order.getProductColor()+" ").append(order.getProductSize()+" ")
                .append(order.getCount()+"개").toString();

    }



}
