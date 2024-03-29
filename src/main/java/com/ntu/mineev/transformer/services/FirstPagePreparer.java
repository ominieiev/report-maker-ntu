package com.ntu.mineev.transformer.services;

import com.ntu.mineev.transformer.DTO.InputData;
import com.ntu.mineev.transformer.model.UniversityInfoService;
import com.ntu.mineev.transformer.services.utilities.StringUtility;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FirstPagePreparer {
    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    UniversityInfoService universityInfoService;


    public XSSFWorkbook prepareFirstPageAndStub(XSSFWorkbook workbook, InputData inputInfo) throws IOException {
        prepareStub(workbook);
        //inputInfo = StringUtility.getRandomInputData();
        XSSFSheet ws = workbook.getSheetAt(0);
        ws.getRow(24).getCell(1).
                setCellValue(inputInfo.getFirstName() + " " + inputInfo.getLastName()); //B25

        ws.getRow(6).getCell(3).
                setCellValue(inputInfo.getFacultyName()); //D7

        ws.getRow(8).getCell(3).
                setCellValue(inputInfo.getDepartmentName());//D9
        ws.getRow(45).getCell(2).
                setCellValue(inputInfo.getDepartmentName());//C46
        ws.getRow(45).getCell(3).
                setCellValue(universityInfoService.getDepartmentBoss(inputInfo.getDepartmentName()));//D46

        ws.getRow(10).getCell(3).
                setCellValue(inputInfo.getLastName() + " " + StringUtility.transformNameToInitials(inputInfo.getFirstName()));//D11
        ws.getRow(45).getCell(6).
                setCellValue(inputInfo.getLastName() + " " + StringUtility.transformNameToInitials(inputInfo.getFirstName()));//G46


        ws.getRow(27).getCell(3).
                setCellValue(inputInfo.getContractFrom());//D28

        ws.getRow(27).getCell(4).
                setCellValue(inputInfo.getContractTo());//E28

        ws.getRow(31).getCell(1).
                setCellValue(StringUtility.getCurrentEducationalYearDiapazone());//B32
        ws.getRow(31).getCell(2).
                setCellValue(inputInfo.getJobTitle());//C32
        ws.getRow(31).getCell(3).
                setCellValue(inputInfo.getScientificLevel());//D32
        ws.getRow(31).getCell(4).
                setCellValue(inputInfo.getRank());//E32
        ws.getRow(31).getCell(5).
                setCellValue(inputInfo.getWorkingPart());//F32

        return workbook;
    }

    private void prepareStub(XSSFWorkbook workbook) {
        workbook.getSheetAt(1).getRow(4).getCell(0).setCellValue("1 Розподіл навчальної роботи в годинах на" + StringUtility.getCurrentEducationalYearDiapazone() + "навчальний рік");

        workbook.getSheetAt(1).getRow(136).getCell(44).setCellValue(  StringUtility.getCurrentEducationalYearTo() );
        workbook.getSheetAt(2).getRow(135).getCell(44).setCellValue(  StringUtility.getCurrentEducationalYearTo() );
    }
}
