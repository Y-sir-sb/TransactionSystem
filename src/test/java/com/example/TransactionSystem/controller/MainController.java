package com.example.TransactionSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {
    @GetMapping(value = "regedit")
    public String Regedit(){
        return "regeditTable";
    }

}
