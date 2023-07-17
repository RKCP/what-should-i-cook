package com.raphael.whatshouldicook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RecipeService {

    public String uploadImage(MultipartFile file) {


        return "Success!";
    }
}
