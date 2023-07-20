package com.raphael.whatshouldicook.controller;

import com.raphael.whatshouldicook.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String index() {
        System.out.println("at index now asdasdasdaasd");
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("fileInput") MultipartFile multipartFile, Model model) {

        // Process the uploaded file
        // service method that sends it to s3...
        service.uploadImage(multipartFile, multipartFile.getOriginalFilename());


        // Add the uploaded file information to the model
        model.addAttribute("fileName", multipartFile.getOriginalFilename());
        System.out.println("hitting upload method");
        return "upload-success"; // Redirect back to the index page
    }
}
