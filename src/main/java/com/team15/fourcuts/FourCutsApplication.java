package com.team15.fourcuts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FourCutsApplication {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(FourCutsApplication.class, args);
    }

}
