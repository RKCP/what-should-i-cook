package com.raphael.whatshouldicook.service;

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

import java.io.IOException;

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


    public String uploadImageAndAnalyzeWithRekognition(MultipartFile file, String fileName) {

        RekognitionClient  rekognition = RekognitionClient.builder()
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

        byte[] finalFileBytes = fileBytes;
        DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder()
                .image(builder -> builder.bytes(SdkBytes.fromByteArray(finalFileBytes)))
                .maxLabels(50)
                .build();

        DetectLabelsResponse detectLabelsResponse =  rekognition.detectLabels(detectLabelsRequest);

        // attempt with custom labels since detectlabels with default amazon training isn't picking up the labels we want.
        DetectCustomLabelsRequest detectCustomLabelsRequest  = DetectCustomLabelsRequest.builder()
                .image(builder -> builder.bytes(SdkBytes.fromByteArray(finalFileBytes)))
                .maxResults(100)
                .build();

        DetectCustomLabelsResponse detectCustomLabelsResponse = rekognition.detectCustomLabels(detectCustomLabelsRequest);

        return "Success";
    }
}


// Perhaps do image processing here instead of sending to s3 and doing as a lambda function in the cloud. Us AWS Rekognition in the Service Layer.\

// Next up do local processing with rekognition from aws sdk