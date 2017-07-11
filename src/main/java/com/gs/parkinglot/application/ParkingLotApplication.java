package com.gs.parkinglot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by govinda.v on 11/07/17.
 */
@SpringBootApplication(scanBasePackages = "com.gs")
public class ParkingLotApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ParkingLotApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ParkingLotApplication.class);
    }
}
