package com.nice.nicebaby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceApplication.class, args);
    }

}
