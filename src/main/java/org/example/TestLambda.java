package org.example;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;
import java.util.Map;

public class TestLambda implements RequestHandler<Map<String,String>,String> {

    private static final AWSCredentials AWS_CREDENTIALS;

    static {
        // Your accesskey and secretkey
        AWS_CREDENTIALS = new BasicAWSCredentials(
                "accesskey",
                "secretkey"
        );
    }
    @Override
    public String handleRequest(Map<String, String> stringStringMap, Context context) {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(AWS_CREDENTIALS))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        List<Bucket> buckets = s3client.listBuckets();
        StringBuilder sb = new StringBuilder();
        int i=1;
        sb.append("This application created on AWS LAMBDA and print information about all my S3 buckets:           ");
        for (Bucket bucket : buckets) {
            sb.append(i).append(")").append(bucket.getName()).append("                 ");
            i++;
        }
         return sb.toString();
    }
}