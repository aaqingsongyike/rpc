package com.example.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
//@EnableHystrix
public class SpringrpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrpcApplication.class, args);
    }

}
