package com.wyh.yygh.cmn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *service_cmn的启动类
 *
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.wyh")
public class ServiceCmnApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplicaton.class,args);
    }

}
