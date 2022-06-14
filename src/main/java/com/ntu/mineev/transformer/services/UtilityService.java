package com.ntu.mineev.transformer.services;

import com.ntu.mineev.transformer.model.htmlview.Dictionary;
import com.ntu.mineev.transformer.model.htmlview.DictionaryConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UtilityService {
    private static final String FILE_EXTENSION = ".xlsx";
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_DEPARTMENT_LENGTH = 5;

    public String prepareFileNameForUpload(String email, String department) {
       String name = email.split("@")[0];
       if (name.length()>MAX_NAME_LENGTH) {
           name=name.substring(0,MAX_NAME_LENGTH);
        }
        String departmentName=department;
        if (departmentName.length()>MAX_DEPARTMENT_LENGTH) {
            departmentName=departmentName.substring(0,MAX_DEPARTMENT_LENGTH);
        }

        String validTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
        return departmentName + "_" + name + "_" + validTime + FILE_EXTENSION;
    }

    public ResponseEntity<ByteArrayResource> prepareResultFileResponse(Path path) throws IOException {
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + getFileNameFromPath(path))
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(data.length) //
                .body(resource);
    }

    public String getFileNameFromPath(Path path) {
        String pathStr = path.toString();
        int length = pathStr.length();
        int countOfDirs = pathStr.split("/").length;
        return pathStr.split("/")[countOfDirs - 1];
    }

    public Dictionary getDictionary() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DictionaryConfig.class);
        return context.getBean(Dictionary.class);

    }

}
