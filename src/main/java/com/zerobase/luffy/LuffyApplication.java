package com.zerobase.luffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LuffyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuffyApplication.class, args);
    }

}
