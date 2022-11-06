package com.zerobase.luffy.batch;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MarketId;

    private String title;
    private Long price;
}
