package com.zerobase.luffy.member.type;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ManagerProductCode {

    Processing("등록"),
    UnProcessing("임시등록"),
    SoldOut("일시품절");

    private final String description;
    ManagerProductCode(String description){
        this.description = description;
    }

    private static final Map<String, ManagerProductCode>descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ManagerProductCode::getDescription, Function.identity())));

    //수정이 불가한 map을 선언 values로 맵에 저장,


    public static ManagerProductCode find(String description){
      return  Optional.ofNullable(descriptions.get(description)).orElse(UnProcessing);
    }

}
