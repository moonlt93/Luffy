package com.zerobase.luffy.Util.base;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseHeader {


         @CreatedDate
         @Column(updatable = false)
        private LocalDateTime RegDt;

         @LastModifiedDate
         private LocalDateTime upDt;



}
