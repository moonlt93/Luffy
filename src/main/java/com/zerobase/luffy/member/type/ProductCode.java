package com.zerobase.luffy.member.type;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ProductCode {

    SellOK("판매중"),
    SellNo("판매불가"),
    Processing("처리중"),
    WareHousing("입고 준비중"),
    SoldOut("일시품절");

    private final String description;
    ProductCode(String description){
        this.description = description;
    }

    private static final Map<String,ProductCode>descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ProductCode::getDescription, Function.identity())));

    //수정이 불가한 map을 선언 values로 맵에 저장,


    public static ProductCode find(String description){
      return  Optional.ofNullable(descriptions.get(description)).orElse(SoldOut);
    }

}
