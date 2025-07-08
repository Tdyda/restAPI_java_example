package com.example.restapi_java;

import com.example.restapi_java.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiJavaApplication {

    public static void main(String[] args) {
        DotenvConfig.loadEnv();
        SpringApplication.run(RestApiJavaApplication.class, args);
    }

}
