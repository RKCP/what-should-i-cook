package com.raphael.whatshouldicook.controller;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeServiceMock;

    @InjectMocks
    private RecipieController recipieController;



    @Test
    void getRecipeFromImage_ReturnsRecipeBasedOnImage() {


        // Given
        String filePath = "/path/to/local/image.jpg"; // Replace with the path to the local image file
        try {
            MultipartFile multipartFile = createMultipartFileFromLocalFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
            //logger
        }

        when(recipeServiceMock.uploadImage(file)).thenReturn("Success!");

        // When
        String imageUpload = recipieController.getRecipieFromImage(file);

        // Then
        assertEquals("Success!", imageUpload);
    }










    // Helper Method
    private MultipartFile createMultipartFileFromLocalFile(String filePath) throws IOException {
        Path fileLocation = Paths.get(filePath);
        String fileName = fileLocation.getFileName().toString();
        String contentType = Files.probeContentType(fileLocation);
        byte[] fileContent = Files.readAllBytes(fileLocation);

        return new MockMultipartFile(fileName, fileName, contentType, fileContent);
    }
}
