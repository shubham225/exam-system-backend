package com.exam.system.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO : Implement a custom error page
//@RestController
public class CustomErrorController implements ErrorController {
    //@GetMapping("/error")
    public String handleError() {
        return "error";
    }
}
