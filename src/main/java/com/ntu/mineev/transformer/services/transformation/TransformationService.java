package com.ntu.mineev.transformer.services.transformation;

import com.ntu.mineev.transformer.services.FileManagerService;
import com.ntu.mineev.transformer.services.UtilityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TransformationService {
    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    UtilityService utilityService;

    public Path transform(Path path) throws IOException {
        XSSFWorkbook wb = fileManagerService.openExcelFile(path.toString());
        wb = prepareStructure(wb);


        Path transformedPath = Paths.get(FileManagerService.TRANSFORMED_FOLDER + utilityService.getFileNameFromPath(path));
        fileManagerService.saveExcelFile(wb, transformedPath.toString());
        return transformedPath;
    }


    private XSSFWorkbook prepareStructure(XSSFWorkbook wb) {
        wb.createSheet("Datatypes in Java");
        wb.createSheet(" in Java");
        wb.createSheet("Datatypes in ");
        return wb;
    }

}