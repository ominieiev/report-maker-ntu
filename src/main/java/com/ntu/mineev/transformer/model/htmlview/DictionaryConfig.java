package com.ntu.mineev.transformer.model.htmlview;

import com.ntu.mineev.transformer.model.ScienceType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DictionaryConfig {
    @Bean
    public Dictionary init() {
        Dictionary dictionary= new Dictionary();

        List<ScienceType> scienceTypes= new ArrayList<>();
        scienceTypes.add(new ScienceType("NAME1","VALUE1"));
        scienceTypes.add(new ScienceType("NAME2","VALUE2"));
        scienceTypes.add(new ScienceType("NAME3","VALUE3"));
        scienceTypes.add(new ScienceType("NAME4","VALUE4"));
        scienceTypes.add(new ScienceType("NAME5","VALUE5"));
        dictionary.setScienceTypes(scienceTypes);
        return dictionary;
    }
}
