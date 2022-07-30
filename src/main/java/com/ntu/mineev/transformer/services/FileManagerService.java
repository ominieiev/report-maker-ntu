package com.ntu.mineev.transformer.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileManagerService {
    public static final String UPLOADED_FOLDER = "src/main/resources/uploadedFiles/";
    public static final String EXCEL_STUB_PATH = "src/main/resources/excel_stubs/stub.xlsx";
    public static final String TRANSFORMED_FOLDER = "src/main/resources/transformedFiles/";
    public static final String RESOURSE_MAIN_VIEW = "src/main/resources/mainView.xml";
    @Autowired
    UtilityService utilityService;

    public Path saveFile(String email, String department, MultipartFile[] files) throws IOException {

        MultipartFile file = files[0];
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + utilityService.prepareFileNameForUpload(email, department));
        Files.write(path, bytes);
        return path;
    }

    public void deleteUploadedFile(Path uploadedPath) throws IOException {
        Files.delete(uploadedPath);
    }
    public String readTextFile(String strPath) throws IOException {
        Path path = Paths.get(strPath);
        return String.join(" ",Files.readAllLines(path));
    }

    public XSSFWorkbook openExcelFile(String path) throws IOException {
      FileInputStream excelFile = new FileInputStream(new File(path));
        XSSFWorkbook wb = new XSSFWorkbook(excelFile);
        return wb;
    }

    public void saveExcelFile(XSSFWorkbook workbook, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        workbook.close();

    }
    public XSSFWorkbook getStubExcel() throws IOException {
        return  openExcelFile(String.valueOf(Paths.get(EXCEL_STUB_PATH )));

    }
}
