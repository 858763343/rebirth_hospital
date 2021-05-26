package com.wyh.yygh.hosp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *service_hosp的启动类
 *
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.wyh")
public class ServiceHospApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplicaton.class,args);
    }

}
