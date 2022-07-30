package com.ntu.mineev.transformer.services.parcers;

import lombok.Getter;

@Getter
public enum EducationType {
    ONSITE("Денна"), //очне
    OFFSHORE("Заочна"),//заочне
    ASPIR_AND_DOCTOR("Аспір");//Відділ Аспірантури і докторантури
    String value;

    EducationType(String value) {
        this.value = value;
    }
}
