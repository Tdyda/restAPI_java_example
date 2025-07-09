package com.example.restapi_java;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class RestApiJavaApplicationTests {

    @DynamicPropertySource
    static void loadDotenv(DynamicPropertyRegistry registry) {
        Dotenv dotenv = Dotenv.configure().filename(".env.local").load();
        dotenv.entries().forEach(entry -> {
            registry.add(entry.getKey(), entry::getValue);
        });
    }

    @Test
    void contextLoads() {
    }
}
