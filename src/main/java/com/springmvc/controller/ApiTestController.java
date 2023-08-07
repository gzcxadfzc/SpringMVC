package com.springmvc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiTestController {

    @RequestMapping(value = "/api")
    public String restTest(@RequestParam String str) {
        return str + " : Rest Test 완료!!!";
    }
}
