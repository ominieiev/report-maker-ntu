package com.ntu.mineev.transformer.services.parcers;

import com.ntu.mineev.transformer.model.report.Discipline;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelParser {
    private static final int INDEX_OF_NAME_AND_DEPART_ROW = 2;

    public List<Discipline> parceExcelDisciplines(XSSFWorkbook wb) {
        List<Discipline> result = new ArrayList<>();
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            result.addAll(parceExcelSheet(wb.getSheetAt(i)));
        }
        return result;
    }

    public String parceExcelName(XSSFWorkbook wb) {
        String mainStr = wb.getSheetAt(0).getRow(INDEX_OF_NAME_AND_DEPART_ROW).toString();
        mainStr = mainStr.split(":")[0];
        mainStr = mainStr.split(",")[0];
        return mainStr;
    }

    public String parceExcelDepartmentName(XSSFWorkbook wb) {
        String mainStr = wb.getSheetAt(0).getRow(INDEX_OF_NAME_AND_DEPART_ROW).toString();
        mainStr = mainStr.split(":")[0];
        mainStr = mainStr.replace(parceExcelName(wb), "");
        return mainStr;
    }

    private List<Discipline> parceExcelSheet(XSSFSheet sheet) {
        EducationType educationType = EducationType.ONSITE;
        List<Discipline> result = new ArrayList<>();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            XSSFRow row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null) {
                continue;
            }

            for (int j = 0; j < EducationType.values().length; j++) {
                if (row.getCell(0).toString().contains(EducationType.values()[j].getValue())) {
                    educationType = EducationType.values()[j];
                }
            }

            if (isNumeric(row.getCell(0).toString())) {
                result.add(parceExcelDiscipline(sheet.getRow(i), educationType));
            }
        }
        return result;
    }

    private Discipline parceExcelDiscipline(XSSFRow row, EducationType educationType) {
        Discipline discipline = new Discipline();

        discipline.setTeacherName(getValue(row.getCell(InputCellAddresses.TEACHER_NAME.getIndexOfInputColumn())));
        discipline.setNameOfDiscipline(getValue(row.getCell(InputCellAddresses.NAME_OF_DISCIPLINE.getIndexOfInputColumn())));
        discipline.setQuater(Float.parseFloat(getValue(row.getCell(InputCellAddresses.QUATER.getIndexOfInputColumn()))));
        discipline.setLevelOfGroup(Float.parseFloat(getValue(row.getCell(InputCellAddresses.LEVEL_OF_GROUP.getIndexOfInputColumn()))));
        discipline.setGroupsName(getValue(row.getCell(InputCellAddresses.GROUPSNAME.getIndexOfInputColumn())));
        discipline.setSemester(Float.parseFloat(getValue(row.getCell(InputCellAddresses.SEMESTER.getIndexOfInputColumn()))));
        discipline.setCountOfGroup(Float.parseFloat(getValue(row.getCell(InputCellAddresses.COUNT_OF_GROUP.getIndexOfInputColumn()))));
        discipline.setCountOfStudents(Float.parseFloat(getValue(row.getCell(InputCellAddresses.COUNT_OF_STUDENTS.getIndexOfInputColumn()))));
        discipline.setTimeForLearning(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_LEARNING.getIndexOfInputColumn()))));
        discipline.setCountOfWeeks(Float.parseFloat(getValue(row.getCell(InputCellAddresses.COUNT_OF_WEEKS.getIndexOfInputColumn()))));
        discipline.setPerWeekLectures(Float.parseFloat(getValue(row.getCell(InputCellAddresses.PER_WEEK_LECTURES.getIndexOfInputColumn()))));
        discipline.setPerWeekLaboratories(Float.parseFloat(getValue(row.getCell(InputCellAddresses.PER_WEEK_LABORATORIES.getIndexOfInputColumn()))));
        discipline.setPerWeekPractic(Float.parseFloat(getValue(row.getCell(InputCellAddresses.PER_WEEK_PRACTIC.getIndexOfInputColumn()))));
        discipline.setPerWeekIndividual(Float.parseFloat(getValue(row.getCell(InputCellAddresses.PER_WEEK_INDIVIDUAL.getIndexOfInputColumn()))));
        discipline.setTimeForLectures(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_LECTURES.getIndexOfInputColumn()))));
        discipline.setTypeOfResults(getValue(row.getCell(InputCellAddresses.TYPE_OF_RESULTS.getIndexOfInputColumn())));
        discipline.setTimeForPractice(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_PRACTICE.getIndexOfInputColumn()))));
        discipline.setTimeForLaboratory(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_LABORATORY.getIndexOfInputColumn()))));
        discipline.setTimeForPhDExams(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_PHD_EXAMS.getIndexOfInputColumn()))));
        discipline.setTimeForPass(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_PASS.getIndexOfInputColumn()))));
        discipline.setTimeForFinalExamsAndConsult(getValue((row.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS_AND_CONSULT.getIndexOfInputColumn()))));

        discipline.setTimeForConsultFinalExams(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_CONSULT_FINAL_EXAMS.getIndexOfInputColumn()))));
        discipline.setTimeForFinalExams(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS.getIndexOfInputColumn()))));
        discipline.setTimeForPassingFinalDiplomas(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_PASSING_FINAL_DIPLOMAS.getIndexOfInputColumn()))));
        discipline.setTimeForPhDConsult(Float.parseFloat(getValue(row.getCell(InputCellAddresses.TIME_FOR_PHD_CONSULT.getIndexOfInputColumn()))));
        discipline.setTotalTime(Float.parseFloat(row.getCell(InputCellAddresses.TOTAL_TIME.getIndexOfInputColumn()).getRawValue()));

        discipline.setEducationType(educationType);
        return discipline;
    }

    private String getValue(XSSFCell cell) {
        if (cell.toString() == "") {
            return "0";
        }
        if (cell.getCTCell().getF() != null) {
            return cell.getCTCell().getV();
        }
        return cell.toString();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            try {
                Float.parseFloat(str);
                return true;
            } catch(NumberFormatException ee){
                return false;
            }
        }
    }
}
