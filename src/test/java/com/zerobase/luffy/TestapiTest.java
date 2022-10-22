package com.zerobase.luffy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class TestapiTest {


@Test
void getfile(){
//given
Assertions.assertEquals(1, 1);
//when

//then

}
    @Test
    void getfile2(){
//given
        Assertions.assertEquals(2, 2);
//when

//then

    }


}