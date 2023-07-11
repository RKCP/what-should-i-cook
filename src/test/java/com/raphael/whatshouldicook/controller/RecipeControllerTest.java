package com.raphael.whatshouldicook.controller;

import com.raphael.whatshouldicook.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeServiceMock;

    @InjectMocks
    private RecipeController recipeController;


//    @Test
//    void handleFileUpload_ReturnStringAfterImageUploaded() {
//
//
//        // Given
//        String filePath = "src/test/resources/fridge.jpg"; // Replace with the path to the local image file
//        MultipartFile multipartFile;
//        try {
//            multipartFile = createMultipartFileFromLocalFile(filePath);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//            //logger
//        }
//
//        //when(recipeServiceMock.uploadImage(multipartFile)).thenReturn("Success!");
//
//        // When
//        String imageUpload = recipeController.handleFileUpload(multipartFile);
//
//        // Then
//        assertEquals("Success!", imageUpload);
//    }










    // Helper Method
    private MultipartFile createMultipartFileFromLocalFile(String filePath) throws IOException {
        Path fileLocation = Paths.get(filePath);
        String fileName = fileLocation.getFileName().toString();
        String contentType = Files.probeContentType(fileLocation);
        byte[] fileContent = Files.readAllBytes(fileLocation);

        return new MockMultipartFile(fileName, fileName, contentType, fileContent);
    }
}
