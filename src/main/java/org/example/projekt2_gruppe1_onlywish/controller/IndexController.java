package org.example.projekt2_gruppe1_onlywish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }
}

