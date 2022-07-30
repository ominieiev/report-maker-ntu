package com.ntu.mineev.transformer.controllers;

import com.ntu.mineev.transformer.DTO.InputData;
import com.ntu.mineev.transformer.model.htmlview.Dictionary;
import com.ntu.mineev.transformer.model.Element;
import com.ntu.mineev.transformer.services.FileManagerService;
import com.ntu.mineev.transformer.services.FirstPagePreparer;
import com.ntu.mineev.transformer.services.UtilityService;
import com.ntu.mineev.transformer.services.transformation.TransformationService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InjectController {

    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    FirstPagePreparer firstPagePreparer;
    @Autowired
    TransformationService transformationService;
    @Autowired
    UtilityService utilityService;

    @Autowired
    Dictionary dictionary;
    String firstName;
    String lastName;
    String facultyName;
    String departmentName;
    String contractFrom;
    String contractTo;
    String workingPart;
    String rank;//Вчене звання
    String scientificLevel;//Науковий ступінь
    String jobTitle;

    @PostMapping("/inject")
    ResponseEntity<ByteArrayResource> postInject(@RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String facultyName,
                                                 @RequestParam String departmentName,
                                                 @RequestParam String contractFrom,
                                                 @RequestParam String contractTo,
                                                 @RequestParam String workingPart,
                                                 @RequestParam String rank,
                                                 @RequestParam String scientificLevel,
                                                 @RequestParam String jobTitle,
                                                 @RequestParam("file") MultipartFile[] files) throws IOException {
        InputData inputData = new InputData(firstName,
                lastName,
                facultyName,
                departmentName,
                contractFrom,
                contractTo,
                workingPart,
                rank,
                scientificLevel,
                jobTitle);

        Path savedFile = fileManagerService.saveFile(lastName + "@", departmentName, files);
        Path transformedFile = transformationService.transform(savedFile,inputData);
        // fileManagerService.deleteUploadedFile(uploadedPath);

        return utilityService.prepareResultFileResponse(transformedFile);
    }

    @GetMapping("/inject")
    String getInject() {

        return "hello";
    }

    @GetMapping("/testing")
    ResponseEntity<ByteArrayResource> getIn() throws IOException {
        XSSFWorkbook firstPageWb = firstPagePreparer.prepareFirstPage(fileManagerService.getStubExcel(), null);
        Path transformedPath = Paths.get(FileManagerService.TRANSFORMED_FOLDER + "/1.xlsx");
        fileManagerService.saveExcelFile(firstPageWb, transformedPath.toString());
        return utilityService.prepareResultFileResponse(transformedPath);
    }

    @GetMapping("/")
    String welcome(Model model) throws IOException {
        utilityService.getDictionary();
        List<Element> faculties = new ArrayList<>();
        faculties.add(new Element("ФІТ", "ФІТ"));
        faculties.add(new Element("ЕТФ", "ЕТФ"));
        faculties.add(new Element("ГРФ", "ГРФ"));
        faculties.add(new Element("ММФ", "ММФ"));
        model.addAttribute("faculties", faculties);


        return "index";
    }
}
