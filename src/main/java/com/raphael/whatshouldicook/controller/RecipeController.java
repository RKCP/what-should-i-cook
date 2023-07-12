package com.raphael.whatshouldicook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RecipeController {


    @GetMapping("/")
    public String index() {
        System.out.println("at index now asdasdasdaasd");
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {

        // Process the uploaded file
        // ...
        System.out.println("hitting upload method");
        return "index"; // Redirect back to the index page
    }
}
