package com.ntu.mineev.transformer.model.htmlview;

import com.ntu.mineev.transformer.model.ScienceType;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
@Component
@Data
public class Dictionary {
    List<ScienceType> scienceTypes;




}
