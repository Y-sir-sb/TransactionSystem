package com.example.TransactionSystem.impl;

import com.example.TransactionSystem.empty.Message;
import com.example.TransactionSystem.utils.FileloadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Logger;

public class HeardUpServer {
    private static final String  upfileUrl = "";
    private static final Logger logger = Logger.getLogger(HeardUpServer.class.getName());
    FileloadUtil fileloadUtil = new FileloadUtil();
    public boolean upServer(String uploadPath,String upfilename){

        String fileuploadPath = uploadPath;
        String Upfilename = upfilename;
        boolean uploadSuccess = fileloadUtil.uploadFile(fileuploadPath,upfileUrl);
        if(!uploadSuccess){
           logger.severe("upload failed");
        }else{
            return true;
        }
        return true;

    }
}
