package com.example.rpc.config;import com.example.rpc.util.annotation.EnableRPC;import org.springframework.context.annotation.Configuration;@Configuration@EnableRPC(basePackage = {"com.example.rpc.service"})public class RPCConfig {}