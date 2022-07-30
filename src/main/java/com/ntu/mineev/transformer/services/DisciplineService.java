package com.ntu.mineev.transformer.services;

import com.ntu.mineev.transformer.model.report.Discipline;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {
   public int getFirstOrSecondSemestr(Discipline discipline) {
        if ((int) discipline.getSemester() % 2 == 0) {
            return 2;
        }
        return 1;
    }
}
