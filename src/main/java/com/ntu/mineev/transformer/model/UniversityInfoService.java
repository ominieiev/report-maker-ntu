package com.ntu.mineev.transformer.model;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UniversityInfoService {
    public String getDepartmentBoss(String department){
        Map<String,String> bosses= new HashMap<>();
        bosses.put("САіУ","Желдак Т.А.");
        bosses.put("ПЗКС","Алексєєв М.А.");
        bosses.put("БІТ","Корнієнко В.І.");

        return bosses.get(department);
    }
}
