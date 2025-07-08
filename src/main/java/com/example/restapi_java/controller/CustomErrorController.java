package com.example.restapi_java.controller;

import com.example.restapi_java.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        var webRequest = new ServletWebRequest(request);
        Map<String, Object> attrs = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE)
        );

        int status = (int) attrs.getOrDefault("status", 500);

        if (status != 500) {
            return null;
        }

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status)
                .error((String) attrs.getOrDefault("error", "Unexpected Error"))
                .path((String) attrs.getOrDefault("path", request.getRequestURI()))
                .errors(Map.of("error", (String) attrs.getOrDefault("message", "Unknown Error")))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
