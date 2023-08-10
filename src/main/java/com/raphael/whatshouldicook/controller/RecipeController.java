package com.raphael.whatshouldicook.controller;

import com.raphael.whatshouldicook.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }


//    @GetMapping("/")
//    public String index() {
//        System.out.println("at index now asdasdasdaasd");
//        return "index";
//    }

    @PostMapping("/findRecipe")
    public String findRecipeFromIngredients(@RequestParam("fileInput") MultipartFile multipartFile, Model model) {

        // Process the uploaded file
        // service method that sends it to s3...
        service.uploadImageAndSendToS3Bucket(multipartFile, multipartFile.getOriginalFilename());


        // Add the uploaded file information to the model
        model.addAttribute("fileName", multipartFile.getOriginalFilename());
        System.out.println("hitting upload method");
        return "upload-success"; // Redirect back to the index page
        // instead of upload success, need to redirect to the view of the recipe
    }

    @GetMapping("/")
    public String showSVGGallery(Model model) {
        // Get the list of SVG file names from the "svg_files" directory
        List<String> svgFiles = service.getSVGFilesList();

        model.addAttribute("svgFiles", svgFiles);
        return "index";
    }

}
