package com.ntu.mineev.transformer.controllers;

import com.ntu.mineev.transformer.model.htmlview.Dictionary;
import com.ntu.mineev.transformer.model.Element;
import com.ntu.mineev.transformer.services.FileManagerService;
import com.ntu.mineev.transformer.services.UtilityService;
import com.ntu.mineev.transformer.services.transformation.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InjectController {

    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    TransformationService transformationService;
    @Autowired
    UtilityService utilityService;

    @Autowired
    Dictionary dictionary;

    @PostMapping("/inject")
    ResponseEntity<ByteArrayResource> postInject(@RequestParam String fname,
                                                 @RequestParam String lname,
                                                 @RequestParam String email,
                                                 @RequestParam String department,
                                                 @RequestParam String style,
                                                 @RequestParam String element,
                                                 @RequestParam("file") MultipartFile[] files) throws IOException {




        Path uploadedPath = fileManagerService.saveFile(email, department, files);
        Path transformedPath = transformationService.transform(uploadedPath);
        // fileManagerService.deleteUploadedFile(uploadedPath);

        return utilityService.prepareResultFileResponse(transformedPath);
    }

    @GetMapping("/inject")
    String getInject() {

        return "hello";
    }

    @GetMapping("/")
    String welcome(Model model) throws IOException {
        utilityService.getDictionary();
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("1", "100"));
        elementList.add(new Element("2", "200"));
        elementList.add(new Element("3", "300"));
        elementList.add(new Element("4", "400"));
        model.addAttribute("elements", elementList);
        return "index";
    }
}
