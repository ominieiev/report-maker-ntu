package com.ntu.mineev.transformer.services;

import com.ntu.mineev.transformer.model.report.Discipline;
import com.ntu.mineev.transformer.services.parcers.EducationType;
import com.ntu.mineev.transformer.services.parcers.InputCellAddresses;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SemesterPagesPreparer {
    public static final int FIRST_SEMESTER_ONSHORE_STARTS_FROM = 10;
    public static final int FIRST_SEMESTER_ONSHORE_TOTAL_RESULTS_LINE = 1;

    public static final int FIRST_SEMESTER_OFFSHORE_STARTS_FROM = 117;
    public static final int FIRST_SEMESTER_OFFSHORE_TOTAL_RESULTS_LINE = 1;

    public static final int SECOND_SEMESTER_ONSHORE_STARTS_FROM = 1;
    public static final int SECOND_SEMESTER_ONSHORE_TOTAL_RESULTS_LINE = 10;

    public static final int SECOND_SEMESTER_OFFSHORE_STARTS_FROM = 117;
    public static final int SECOND_SEMESTER_OFFSHORE_TOTAL_RESULTS_LINE = 1;
    @Autowired
    FileManagerService fileManagerService;

    public XSSFWorkbook prepareSemesters(XSSFWorkbook workbook, List<Discipline> disciplines) throws IOException {
        workbook = prepareFirstSemester(workbook, disciplines);
        workbook = prepareSecondSemester(workbook, disciplines);
        return workbook;
    }

    private XSSFWorkbook prepareFirstSemester(XSSFWorkbook wb, List<Discipline> disciplines) {
        XSSFSheet ws = wb.getSheetAt(1);
        List<Discipline> firstSemOnsiteDisc = disciplines.stream().
                filter(discipline -> UtilityService.findFirstOrSecondSemester(discipline.getSemester()) ==1).
                filter(discipline -> discipline.getEducationType() == EducationType.ONSITE).
                collect(Collectors.toList());

        int lineNumber = FIRST_SEMESTER_ONSHORE_STARTS_FROM;
        for (Discipline disc : firstSemOnsiteDisc) {
            XSSFRow row = ws.getRow(lineNumber);
            row = addDiscToRow(disc,  row);
            lineNumber++;
        }

        List<Discipline> firstSemOffshoreDisc = disciplines.stream().
                filter(discipline -> UtilityService.findFirstOrSecondSemester(discipline.getSemester()) ==1).
                filter(discipline -> discipline.getEducationType() == EducationType.OFFSHORE).
                collect(Collectors.toList());

         lineNumber = FIRST_SEMESTER_OFFSHORE_STARTS_FROM;
        for (Discipline disc : firstSemOffshoreDisc) {
            XSSFRow row = ws.getRow(lineNumber);
            row = addDiscToRow(disc, row);
            lineNumber++;
        }
        return wb;
    }

    private XSSFWorkbook prepareSecondSemester(XSSFWorkbook wb, List<Discipline> disciplines) {
        XSSFSheet ws = wb.getSheetAt(2);
        List<Discipline> firstSemOnsiteDisc = disciplines.stream().
                filter(discipline -> UtilityService.findFirstOrSecondSemester(discipline.getSemester()) ==2).
                filter(discipline -> discipline.getEducationType() == EducationType.ONSITE).
                collect(Collectors.toList());

        int lineNumber = SECOND_SEMESTER_ONSHORE_TOTAL_RESULTS_LINE;
        for (Discipline disc : firstSemOnsiteDisc) {
            XSSFRow row = ws.getRow(lineNumber);
            row = addDiscToRow(disc,  row);
            lineNumber++;
        }

        List<Discipline> firstSemOffshoreDisc = disciplines.stream().
                filter(discipline -> UtilityService.findFirstOrSecondSemester(discipline.getSemester()) ==1).
                filter(discipline -> discipline.getEducationType() == EducationType.OFFSHORE).
                collect(Collectors.toList());

        lineNumber = SECOND_SEMESTER_OFFSHORE_STARTS_FROM;
        for (Discipline disc : firstSemOffshoreDisc) {
            XSSFRow row = ws.getRow(lineNumber);
            row = addDiscToRow(disc, row);
            lineNumber++;
        }
        return wb;
    }

    private XSSFRow addDiscToRow(Discipline disc , XSSFRow wsr) {
        wsr.getCell(InputCellAddresses.NAME_OF_DISCIPLINE.getIndexOfFirstOutputColumn()).setCellValue(disc.getNameOfDiscipline());
        wsr.getCell(InputCellAddresses.QUATER.getIndexOfFirstOutputColumn()).setCellValue(disc.getQuater());
        wsr.getCell(InputCellAddresses.LEVEL_OF_GROUP.getIndexOfFirstOutputColumn()).setCellValue(disc.getLevelOfGroup());
        wsr.getCell(InputCellAddresses.GROUPSNAME.getIndexOfFirstOutputColumn()).setCellValue(disc.getGroupsName());
        wsr.getCell(InputCellAddresses.SEMESTER.getIndexOfFirstOutputColumn()).setCellValue(disc.getSemester());
        wsr.getCell(InputCellAddresses.COUNT_OF_GROUP.getIndexOfFirstOutputColumn()).setCellValue(disc.getCountOfGroup());
        wsr.getCell(InputCellAddresses.COUNT_OF_STUDENTS.getIndexOfFirstOutputColumn()).setCellValue(disc.getCountOfStudents());
        wsr.getCell(InputCellAddresses.TIME_FOR_LEARNING.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForLearning());
        wsr.getCell(InputCellAddresses.COUNT_OF_WEEKS.getIndexOfFirstOutputColumn()).setCellValue(disc.getCountOfWeeks());

        wsr.getCell(InputCellAddresses.PER_WEEK_LECTURES.getIndexOfFirstOutputColumn()).setCellValue(disc.getPerWeekLectures());
        wsr.getCell(InputCellAddresses.PER_WEEK_LABORATORIES.getIndexOfFirstOutputColumn()).setCellValue(disc.getPerWeekLaboratories());
        wsr.getCell(InputCellAddresses.PER_WEEK_PRACTIC.getIndexOfFirstOutputColumn()).setCellValue(disc.getPerWeekPractic());
        wsr.getCell(InputCellAddresses.PER_WEEK_INDIVIDUAL.getIndexOfFirstOutputColumn()).setCellValue(disc.getPerWeekIndividual());

        wsr.getCell(InputCellAddresses.TIME_FOR_LECTURES.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForLectures());
        wsr.getCell(InputCellAddresses.TIME_FOR_LECTURES.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForLectures());
        wsr.getCell(InputCellAddresses.TYPE_OF_RESULTS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTypeOfResults());
        wsr.getCell(InputCellAddresses.TYPE_OF_RESULTS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTypeOfResults());
        wsr.getCell(InputCellAddresses.TIME_FOR_PRACTICE.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForPractice());
        wsr.getCell(InputCellAddresses.TIME_FOR_PRACTICE.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForPractice());
        wsr.getCell(InputCellAddresses.TIME_FOR_LABORATORY.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForLaboratory());
        wsr.getCell(InputCellAddresses.TIME_FOR_LABORATORY.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForLaboratory());
        wsr.getCell(InputCellAddresses.TIME_FOR_PHD_EXAMS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForPhDExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_PHD_EXAMS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForPhDExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_PASS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForPass());
        wsr.getCell(InputCellAddresses.TIME_FOR_PASS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForPass());

        wsr.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS_AND_CONSULT.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForFinalExamsAndConsult());
        wsr.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS_AND_CONSULT.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForFinalExamsAndConsult());
        wsr.getCell(InputCellAddresses.TIME_FOR_CONSULT_FINAL_EXAMS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForConsultFinalExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_CONSULT_FINAL_EXAMS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForConsultFinalExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForFinalExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_FINAL_EXAMS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForFinalExams());
        wsr.getCell(InputCellAddresses.TIME_FOR_PASSING_FINAL_DIPLOMAS.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForPassingFinalDiplomas());
        wsr.getCell(InputCellAddresses.TIME_FOR_PASSING_FINAL_DIPLOMAS.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForPassingFinalDiplomas());
        wsr.getCell(InputCellAddresses.TIME_FOR_PHD_CONSULT.getIndexOfFirstOutputColumn()).setCellValue(disc.getTimeForPhDConsult());
        wsr.getCell(InputCellAddresses.TIME_FOR_PHD_CONSULT.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTimeForPhDConsult());
        wsr.getCell(InputCellAddresses.TOTAL_TIME.getIndexOfFirstOutputColumn()).setCellValue(disc.getTotalTime());
        wsr.getCell(InputCellAddresses.TOTAL_TIME.getIndexOfFirstOutputColumn()+1).setCellValue(disc.getTotalTime());
        return wsr;
    }
}
