package com.example.restapi_java.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public abstract class BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected static final Gson gson = new Gson();

    protected ResponseEntity<Void> OK() {
        return ResponseEntity.status(200).build();
    }

    protected <T> ResponseEntity<T> OK(T body) {
        return ResponseEntity.status(200).body(body);
    }

    protected ResponseEntity<Void> CREATED() {
        return ResponseEntity.status(201).build();
    }

    protected <T> ResponseEntity<T> CREATED(T body) {
        return ResponseEntity.status(201).body(body);
    }

    protected <T> ResponseEntity<T> NOCONTENT(T body) {
        return ResponseEntity.status(204).body(body);
    }
}
