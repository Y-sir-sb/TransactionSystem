package com.example.TransactionSystem.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FileUpIoadUtil {
    public static void uploadFile(String FilePath,String uploadUrl) throws IOException {
        File file = new File(FilePath);
        if(!file.exists()){
            System.out.println("FilePath is null" + FilePath);
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uploadUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // Add the file to the multipart entity
            builder.addBinaryBody(
                    "file",
                    file,
                    ContentType.APPLICATION_OCTET_STREAM,
                    file.getName()
            );

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseBody = EntityUtils.toString(responseEntity);
                System.out.println("Server response: " + responseBody);
            }
        }
    }
}
