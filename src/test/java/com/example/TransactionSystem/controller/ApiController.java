package com.example.TransactionSystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @RequestMapping(value = "/regedit" ,method = RequestMethod.POST)
    public String regedit(){
        return "hello word";
    }

}
