package com.zerobase.luffy.member.admin.base;

import com.zerobase.luffy.member.admin.entity.ProductDetail;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ProductHeader {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

         @CreatedDate
        private LocalDateTime RegDt;

         @LastModifiedDate
         private LocalDateTime upDt;



}
