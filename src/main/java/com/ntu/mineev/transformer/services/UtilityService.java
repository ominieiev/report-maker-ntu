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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class UtilityService {
    private static final String FILE_EXTENSION = ".xlsx";
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_DEPARTMENT_LENGTH = 5;

    public String prepareFileNameForUpload(String email, String department) {
        String name = email.split("@")[0];
        if (name.length() > MAX_NAME_LENGTH) {
            name = name.substring(0, MAX_NAME_LENGTH);
        }
        String departmentName = department;
        if (departmentName.length() > MAX_DEPARTMENT_LENGTH) {
            departmentName = departmentName.substring(0, MAX_DEPARTMENT_LENGTH);
        }

        String validTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
        return convertToEnglish(departmentName + "_" + name) + "_" + validTime + FILE_EXTENSION;
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

   static public int findFirstOrSecondSemester(float semester) {
        if (Math.round(semester) % 2 == 0) {
            return 2;
        } else return 1;
    }

    static public String convertToEnglish(String str){

       return str.toLowerCase(Locale.ROOT)
               .replace("а","a")
                .replace("б","b")
                .replace("в","v")
                .replace("г","g")
                .replace("д","d")
                .replace("е","e")
                .replace("й","y")
                .replace("ж","zh")
                .replace("з","z")
                .replace("і","i")
                .replace("ї","yi")
                .replace("к","k")
                .replace("л","l")
                .replace("м","m")
                .replace("н","n")
                .replace("о","o")
                .replace("п","p")
                .replace("р","r")
                .replace("с","s")
                .replace("т","t")
                .replace("у","u")
                .replace("ф","f")
                .replace("х","h")
                .replace("ц","ts")
                .replace("ч","ch")
                .replace("ш","sh")
                .replace("щ","sch")
                .replace("є","ye")
                .replace("ю","yu")
                .replace("я","ya")
                .replace("а","a")
                .replace("б","b")
                .replace("в","v")
                .replace("г","g")
                .replace("д","d")
                .replace("е","e")
                .replace("й","y")
                .replace("ж","zh")
                .replace("з","z")
                .replace("і","i")
                .replace("ї","yi")
                .replace("к","k")
                .replace("л","l")
                .replace("м","m")
                .replace("н","n")
                .replace("о","o")
                .replace("п","p")
                .replace("р","r")
                .replace("с","s")
                .replace("т","t")
                .replace("у","u")
                .replace("ф","f")
                .replace("х","h")
                .replace("ц","ts")
                .replace("ч","ch")
                .replace("ш","sh")
                .replace("щ","sch")
                .replace("є","ye")
                .replace("ю","yu")
                .replace("я","ya");

    };


}
