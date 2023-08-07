package com.example.TransactionSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class ViewController {
    @GetMapping(value = "index")
    public String login (){
        return "index";
        //index.html
    }
    @GetMapping(value = "Page/HomePage")
    public String Home(){
        return "Page/HomePage";
        //HomePage.html
    }
    @GetMapping(value = "Page/regeditPage")
    public String Regedit(){
        return "Page/regeditPage";
        //regeditPage.html
    }

}
