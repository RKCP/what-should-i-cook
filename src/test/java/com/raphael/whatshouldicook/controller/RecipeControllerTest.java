package com.raphael.whatshouldicook.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

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
        MultipartFile file = "";
        when(recipeServiceMock.uploadImage(file)).thenReturn("Success");

        // When
        String imageUpload = recipieController.getRecipieFromImage(file);

        // Then
        assertEquals("Success", imageUpload);
    }
}
