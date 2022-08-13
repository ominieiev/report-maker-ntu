package com.ntu.mineev.transformer.services.parcers;

import lombok.Getter;

@Getter
public enum InputCellAddresses {
    TEACHER_NAME(1),// ПРІЗВИЩЕ ТА ІНІЦІАЛИ, ПОСАДА ВИКЛАДАЧА
    NAME_OF_DISCIPLINE(2,2),//НАЗВА НАВЧАЛЬНИХ ДИСЦИПЛІН І ВИДІВ НАВЧАЛЬНОЇ РОБОТИ
    QUATER(3,3),//ЧВЕРТЬ
    LEVEL_OF_GROUP(4,4),//КУРС НАВЧАННЯ
    GROUPSNAME(5,5),//ШИФР ГРУП (ПОТОКА)
    SEMESTER(6,6),//СЕМЕСТР
    COUNT_OF_GROUP(7,7),//КІЛЬКІСТЬ ГРУП
    COUNT_OF_STUDENTS(8,8),//КІЛЬКІСТЬ СТУДЕНТІВ
    TIME_FOR_LEARNING(9,9),//КІЛЬКІСТЬ СТУДЕНТІВ
    COUNT_OF_WEEKS(10,10), //КІЛЬКІСТЬ ТИЖНІВ

    PER_WEEK_LECTURES(11,11),//НА ТИЖДЕНЬ ЛЕКЦІЇ
    PER_WEEK_LABORATORIES(12,12),//НА ТИЖДЕНЬ ЛАБОРАТОРНІ  РОБОТИ
    PER_WEEK_PRACTIC(13,13),//НА ТИЖДЕНЬ ПРАКТИЧНІ, СЕМІНАРСЬКІ ЗАНЯТТЯ
    PER_WEEK_INDIVIDUAL(14,14),//НА ТИЖДЕНЬ ІНДИВІДУАЛЬНІ РОБОТИ

    TIME_FOR_LECTURES(15,15), //ЧИТАННЯ ЛЕКЦІЙ
    TYPE_OF_RESULTS(21),//КОНТРОЛЬНІ ЗАХОДИ
    TIME_FOR_PRACTICE(23),//ПРОВЕДЕННЯ ПРАКТИЧНИХ, СЕМІНАРСЬКИХ ЗАНЯТЬ
    TIME_FOR_LABORATORY(29),//ПРОВЕДЕННЯ ЛАБОРАТОРНИХ ЗАНЯТЬ
    TIME_FOR_PHD_EXAMS(35),//ПРОВЕДЕННЯ КАНДИДАТСЬКИХ ЕКЗАМЕНІВ
    TIME_FOR_PASS(41),//ПРОВЕДЕННЯ ЗАЛІКУ(ЗАОЧ.)

    TIME_FOR_FINAL_EXAMS_AND_CONSULT(43),//ПРОВЕДЕННЯ СЕМЕСТРОВИХ ЕКЗАМЕНІВ ТА ЕКЗАМЕНАЦІЙНИХ КОНСУЛЬТАЦІЙ
    TIME_FOR_CONSULT_FINAL_EXAMS(49),//КОНСУЛЬТАЦІЇ З АТЕСТАЦІЙНИХ ЕКЗАМЕНІВ
    TIME_FOR_FINAL_EXAMS(51),//ПРОВЕДЕННЯ АТЕСТАЦІЙНИХ ЕКЗАМЕНІВ
    TIME_FOR_PASSING_FINAL_DIPLOMAS(55),//ПРОВЕДЕННЯ ЗАХИСТУ КВАЛІФІКАЦІЙНИХ РОБІТ
    TIME_FOR_PHD_CONSULT(57),//КОНСУЛЬТУВАННЯ ДОКТОРАНТІВ, КЕРІВНИЦТВО ЗДОБУВАЧАМИ ВИЩОЇ ОСВІТИ СТУПЕНЯ ДОКТОРА ФІЛОСОФІЇ
    TOTAL_TIME(59,57);//ВСЬОГО НАВЧАЛЬНОГО НАВАНТАЖЕННЯ ЗА СЕМЕСТР
    private int indexOfInputColumn;
    private int indexOfFirstOutputColumn;
    InputCellAddresses(int indexOfInputColumn) {
        this.indexOfInputColumn = indexOfInputColumn;
        this.indexOfFirstOutputColumn=indexOfInputColumn;
    }
    InputCellAddresses(int indexOfInputColumn,int indexOfFirstOutputColumn) {
        this.indexOfInputColumn = indexOfInputColumn;
        this.indexOfFirstOutputColumn=indexOfFirstOutputColumn;
    }

}
