package com.zerobase.luffy.Exception.ManagerCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ManagerProException {


  private  String description;


    public ManagerProException(String s) {
        this.description = s;
    }
}
