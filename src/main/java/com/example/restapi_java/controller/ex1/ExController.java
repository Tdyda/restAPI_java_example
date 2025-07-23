package com.example.restapi_java.controller.ex1;

import com.example.restapi_java.service.ex.Ex1Console;
import com.example.restapi_java.service.ex.Ex2Console;
import com.example.restapi_java.service.ex.Ex3Console;
import com.example.restapi_java.service.ex.Ex4Console;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ex")
@RequiredArgsConstructor
public class ExController {
    private final Ex1Console ex1;
    private final Ex2Console ex2;
    private final Ex3Console ex3;
    private final Ex4Console ex4;

    @GetMapping("/1")
    public void printEx1() {
        ex1.print();
    }

    @GetMapping("/2")
    public void printEx2() {
        ex2.print();
    }

    @GetMapping("/3")
    public void printEx3() {
        ex3.print();
    }

    @GetMapping("/4")
    public void printEx4() {
        ex4.print();
    }
}
