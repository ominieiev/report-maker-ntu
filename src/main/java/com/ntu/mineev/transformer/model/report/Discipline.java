package com.ntu.mineev.transformer.model.report;

import com.ntu.mineev.transformer.services.parcers.EducationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {
    String teacherName;// Прізвище та ініціали, посада викладача
    String nameOfDiscipline;//Назва навчальних дисциплін і видів навчальної роботи
    float quater;//Чверть
    float levelOfGroup;//Курс навчання
    String groupsName;//Шифр груп (потока)
    float semester;//Семестр
    float countOfGroup;//Кількість груп
    float countOfStudents;//Кількість студентів
    float timeForLearning;//Кількість студентів
    float countOfWeeks; //Кількість тижнів

    float perWeekLectures;//На тиждень Лекції
    float perWeekLaboratories;//На тиждень Лабораторні  роботи
    float perWeekPractic;//На тиждень Практичні, семінарські заняття
    float perWeekIndividual;//На тиждень Індивідуальні роботи

    float timeForLectures; //Читання лекцій
    String typeOfResults;//Контрольні заходи
    float timeForPractice;//Проведення практичних, семінарських занять
    float timeForLaboratory;//Проведення лабораторних занять
    float timeForPhDExams;//Проведення кандидатських екзаменів
    float timeForPass;//Проведення заліку(заоч.)

    String timeForFinalExamsAndConsult;//Проведення семестрових екзаменів та екзаменаційних консультацій
    float timeForConsultFinalExams;//Консультації з атестаційних екзаменів
    float timeForFinalExams;//Проведення атестаційних екзаменів
    float timeForPassingFinalDiplomas;//Проведення захисту кваліфікаційних робіт
    float timeForPhDConsult;//Консультування докторантів, керівництво здобувачами вищої освіти ступеня доктора філософії
    float totalTime;//Всього навчального навантаження за семестр

    EducationType educationType ;// очне\заочне
}
