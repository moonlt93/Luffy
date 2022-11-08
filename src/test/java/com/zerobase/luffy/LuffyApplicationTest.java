package com.zerobase.luffy;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LuffyApplicationTest {

    private final Calculator calculator = new Calculator();

    @Test
    void contextLoads() {

            assertEquals(2, calculator.add(1, 1));
        }
    }

