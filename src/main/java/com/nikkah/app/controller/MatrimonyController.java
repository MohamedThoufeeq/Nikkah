package com.nikkah.app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatrimonyController {

    @GetMapping("/matrimony")
    public String showMatrimonyPage() {
        return "index";
    }
}
