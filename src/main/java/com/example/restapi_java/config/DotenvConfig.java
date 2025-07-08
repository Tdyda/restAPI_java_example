package com.example.restapi_java.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .filename(".env.example")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        Dotenv dotenvLocal = Dotenv.configure()
                .filename(".env.example.local")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        getEnv(dotenv);
        getEnv(dotenvLocal);
    }

    private static void getEnv(Dotenv file) {
        file.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}
