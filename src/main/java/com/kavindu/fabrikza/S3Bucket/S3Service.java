package com.kavindu.fabrikza.S3Bucket;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3;


    public S3Service(S3Client s3) {
        this.s3 = s3;
    }

    public void putObject(String bucketName,String key,byte[] file){
        PutObjectRequest objectRequest=PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    public byte[]  getObject(String bucketname,String key){
        GetObjectRequest getObjectRequest=GetObjectRequest.builder()
                .bucket(bucketname)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response= s3.getObject(getObjectRequest);
        try {
            byte[] bytes= response.readAllBytes();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
