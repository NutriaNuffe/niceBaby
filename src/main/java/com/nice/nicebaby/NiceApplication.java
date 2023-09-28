package com.nice.nicebaby;

import com.nice.nicebaby.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class NiceApplication {

    @Autowired
    private JwtConfig jwtConfig;

    @PostConstruct
    public void init() {
//        JwtUtil.init(jwtConfig.getCrt(), jwtConfig.getKey());
    }

    public static void main(String[] args) {
        SpringApplication.run(NiceApplication.class, args);
    }

}
