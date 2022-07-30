package com.ntu.mineev.transformer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputData {
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
}
