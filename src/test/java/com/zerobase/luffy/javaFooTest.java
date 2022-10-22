package com.zerobase.luffy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class javaFooTest {
    private JavaFoo javaFoo = new JavaFoo();

    @Test
    public void partiallyCoveredHelloMethodTest() {
        String actual = javaFoo.hello("펭");
        assertEquals(actual, "하");
    }
    @Test
    public void partiallyCoveredHelloMethodTest2() {
        String actual = javaFoo.hello("수");
        assertEquals(actual, "화");
    }


}
