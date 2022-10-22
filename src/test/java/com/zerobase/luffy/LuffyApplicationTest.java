package com.zerobase.luffy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LuffyApplicationTest {

    @Test
    public void comment(){
        System.out.println("test");
        int num =1;

        assertEquals(1,num);

    }
}