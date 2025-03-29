package com.hackathon.police_community_app.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueryCategory {
    ASSAULT("Нападение", Priority.CRITICAL),
    ROBBERY("Грабеж", Priority.CRITICAL),
    DOMESTIC_VIOLENCE("Домашнее насилие", Priority.CRITICAL),
    FIRE("Пожар", Priority.CRITICAL),
    MEDICAL_EMERGENCY("Медицинская помощь", Priority.CRITICAL),
    DRUNK_DRIVING("Пьяный водитель", Priority.CRITICAL),
    MISSING_PERSON("Пропажа человека", Priority.CRITICAL),
    BURGLARY("Взлом", Priority.HIGH),
    CAR_THEFT("Угон автомобиля", Priority.HIGH),
    HOOLIGANISM("Хулиганство", Priority.HIGH),
    SUSPICIOUS_BEHAVIOR("Подозрительное поведение", Priority.HIGH),
    TRAFFIC_ACCIDENT("ДТП", Priority.HIGH),
    NATURAL_DISASTER("Стихийное бедствие", Priority.HIGH),
    THEFT("Кража", Priority.MEDIUM),
    VANDALISM("Вандализм", Priority.MEDIUM),
    FRAUD("Мошенничество", Priority.MEDIUM),
    PUBLIC_INTOXICATION("Пьяный дебош", Priority.MEDIUM),
    TRAFFIC_VIOLATION("Нарушение ПДД", Priority.MEDIUM),
    ANIMAL_CRUELTY("Жестокое обращение с животными", Priority.MEDIUM),
    NOISE_COMPLAINT("Шум", Priority.LOW),
    ILLEGAL_GATHERING("Незаконное собрание", Priority.LOW),
    FOUND_ITEM("Находка", Priority.LOW),
    OTHER("Другое", Priority.LOW);

    private final String displayName;
    private final Priority priority;
}