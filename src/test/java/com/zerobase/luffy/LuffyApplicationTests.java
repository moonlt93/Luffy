package com.zerobase.luffy;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@RequiredArgsConstructor
@Transactional
class LuffyApplicationTests {

    @Test
    void sampleTest() {
        assertThat(1, anything());
    }

    @Test
    void equalTest() {
        assertThat(1, equalTo(1));
    }

    @Test
    void equalTest2() {
        assertThat(2, equalTo(2));
    }



}
