package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.member.user.entity.Payment;
import com.zerobase.luffy.member.user.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {


    @Mock
    private PaymentServiceImpl paymentServiceImpl;

    @Mock
    private PaymentRepository paymentRepository;



    @Test
    @DisplayName("락 생성 해제 확인")
    void createLock(){
    //given
        ArgumentCaptor<Payment> lockCaptor = ArgumentCaptor
                .forClass(Payment.class);
        ArgumentCaptor<Payment> unLockCaptor = ArgumentCaptor
                .forClass(Payment.class);

        int result =0;
        for (long i = 0; i < 30 ; i++) {
            Payment pay = Payment.builder()
                    .paymentId(i)
                    .username("진수").build();

             paymentServiceImpl.LockMyself(pay);
             log.info("결제 정보생성: "+i);
             result++;
        }

        Optional<Payment> optionalPayment=paymentRepository.findById(29L);
        Payment payment = null;
        if(optionalPayment.isPresent()) {

            payment = optionalPayment.get();

         assertEquals(payment.getPaymentId(),29L);
        }

       verify(paymentServiceImpl,times(30))
               .LockMyself(lockCaptor.capture());
        verify(paymentServiceImpl,times(30))
                .LockMyself(unLockCaptor.capture());


        assertEquals(30,result);

    }




}