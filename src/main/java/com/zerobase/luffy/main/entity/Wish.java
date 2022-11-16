package com.zerobase.luffy.main.entity;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String productName;
    private String fileUrl;

    private String productUrl;



}
