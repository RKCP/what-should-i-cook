package com.raphael.whatshouldicook.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectCustomLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectCustomLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    public String uploadImageAndSendToS3Bucket(MultipartFile file, String fileName) {

        S3Client s3 = S3Client.builder()
                .region(Region.EU_WEST_2)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        byte[] fileBytes = new byte[0];

        try {
            fileBytes = file.getBytes();
        } catch (IOException e) {
            // logger here
            throw new RuntimeException(e);
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("whatshouldicookbucket")
                .key("whatshouldicookbucket/" + fileName)
                .build();

        PutObjectResponse response = s3.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));

        return response.eTag();
    }



    public List<String> getSVGFilesList() {
        List<String> svgFiles = new ArrayList<>();

        try {
            // Get the ResourcePatternResolver to resolve resources from a pattern
            ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

            // Use the resolver to get all resources matching the pattern "classpath:static/svg_files/*.svg"
            Resource[] resources = resourceResolver.getResources("classpath:svg_files/*.svg");

            // Iterate through the resources and extract their file names
            for (Resource resource : resources) {
                svgFiles.add(resource.getFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return svgFiles;
    }


}


// Perhaps do image processing here instead of sending to s3 and doing as a lambda function in the cloud. Us AWS Rekognition in the Service Layer.\

// Next up do local processing with rekognition from aws sdk