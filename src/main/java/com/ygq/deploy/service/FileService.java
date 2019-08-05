package com.ygq.deploy.service;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

@Service
public class FileService {

    /**
     * 将指定路径文件写到流中
     * 
     * @param stream
     * @param path
     */
    public void write(OutputStream stream, String path) throws IOException {
        try (FileInputStream fis = new FileInputStream(path)) {
            BufferedOutputStream bos = new BufferedOutputStream(stream);
            byte[] buff = new byte[1024];
            int length;
            while ((length = fis.read(buff)) > 0) {
                bos.write(buff, 0, length);
            }
        }
    }
}
