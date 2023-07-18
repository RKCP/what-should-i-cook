package com.raphael.whatshouldicook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

@Service
public class RecipeService {

    public String uploadImage(MultipartFile file) {

        String bucketName = "*** Bucket name ***";
        String stringObjKeyName = "*** String object key name ***";
        String fileObjKeyName = "*** File object key name ***";
        String fileName = "*** Path to file to upload ***";

        Region clientRegion = Region.EU_WEST_1;
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();

        S3Client s3 = S3Client.builder()
                .region(clientRegion)
                .credentialsProvider(credentialsProvider)
                .build();

        byte[] fileBytes = new byte[0];

        try {
            fileBytes = file.getBytes();
        } catch (IOException e) {
            // logger here
            throw new RuntimeException(e);
        }

        PutObjectRequest putOb = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(stringObjKeyName)
                .build();

        PutObjectResponse response = s3.putObject(putOb, RequestBody.fromBytes(fileBytes));

        return response.eTag();
    }
}


// Perhaps do image processing here instead of sending to s3 and doing as a lambda function in the cloud. Us AWS Rekognition in the Service Layer.\

// For now, for simplicity, just send to s3