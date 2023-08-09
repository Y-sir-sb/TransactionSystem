package com.example.TransactionSystem.utils;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FileloadUtil{
   public boolean uploadFile(String filePath, String uploadUrl) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("FilePath is null: " + filePath);
            return false;
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uploadUrl);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "FileName",
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
                return true;  // Return true to indicate success
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Return false to indicate failure
    }
    public boolean DownloadUtil(String Filename,String DownloadUrl){

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(DownloadUrl);

                HttpResponse response = httpClient.execute(httpGet);
                InputStream inputStream = response.getEntity().getContent();

                if (inputStream != null) {
                    try (OutputStream outputStream = new FileOutputStream(Filename)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        System.out.println("File downloaded successfully.");
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("File download failed.");
            return false;
        }

}
