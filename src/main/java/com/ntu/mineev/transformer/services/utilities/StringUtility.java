package com.ntu.mineev.transformer.services.utilities;

import com.ntu.mineev.transformer.DTO.InputData;

import java.time.LocalDateTime;
import java.util.Date;

public class StringUtility {

    public static String transformNameToInitials(String name) {
        String firstLetter = name.split(" ")[0].trim().substring(0, 1) + ".";
        String secondLetter = name.split(" ")[1].trim().substring(0, 1) + ".";
        return firstLetter + secondLetter;
    }

    public static String getCurrentEducationalYear() {
        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTime.getYear() + "-" + (localDateTime.getYear() - 1);
    }
    public static  InputData getRandomInputData(){
        InputData inputData= new InputData();
        inputData.setLastName("Ivanov");
        inputData.setContractFrom("1.01.2021");
        inputData.setContractTo("1.01.2023");
        inputData.setDepartmentName("SAU");
        inputData.setFacultyName("FIT");
        inputData.setJobTitle("proffessor");
        inputData.setRank("proffessor");
        inputData.setScientificLevel("DTN");
        inputData.setWorkingPart("1.1");
        inputData.setFirstName("Ivan Karlovich");
        return inputData;
    }
}

