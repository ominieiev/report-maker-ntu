package com.ntu.mineev.transformer.services.transformation;

import com.ntu.mineev.transformer.DTO.InputData;
import com.ntu.mineev.transformer.model.report.Discipline;
import com.ntu.mineev.transformer.services.FileManagerService;
import com.ntu.mineev.transformer.services.FirstPagePreparer;
import com.ntu.mineev.transformer.services.SemesterPagesPreparer;
import com.ntu.mineev.transformer.services.UtilityService;
import com.ntu.mineev.transformer.services.parcers.ExcelParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TransformationService {
    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    UtilityService utilityService;
    @Autowired
    ExcelParser excelParser;
    @Autowired
    FirstPagePreparer firstPagePreparer;
    @Autowired
    SemesterPagesPreparer semesterPagesPreparer;

    public Path transform(Path path, InputData inputData) throws IOException {
        XSSFWorkbook wb = fileManagerService.openExcelFile(path.toString());
        List<Discipline> disciplineList = excelParser.parceExcelDisciplines(wb);


        XSSFWorkbook report_wb=fileManagerService.getStubExcel();

        //report_wb = firstPagePreparer.prepareFirstPage(report_wb, inputData);
       // semesterPagesPreparer.prepareSemesters(report_wb, disciplineList);

        Path transformedPath = Paths.get(FileManagerService.TRANSFORMED_FOLDER + utilityService.getFileNameFromPath(path));
        fileManagerService.saveExcelFile(report_wb, transformedPath.toString());
        return transformedPath;
    }


    private XSSFWorkbook prepareStructure(XSSFWorkbook wb) {
        wb.createSheet("Datatypes in Java");
        wb.createSheet(" in Java");
        wb.createSheet("Datatypes in ");
        return wb;
    }

}