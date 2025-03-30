package com.hackathon.police_community_app.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidEnumValidator.class) // Указываем класс-валидатор
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {
    // Класс enum, который нужно проверять
    Class<? extends Enum<?>> enumClass();

    // Сообщение об ошибке (можно задать по умолчанию или переопределить)
    String message() default "Недопустимое значение. Допустимые варианты: {enumValues}";

    // Группы валидации (стандартное для Spring Validation)
    Class<?>[] groups() default {};

    // Payload (стандартное для Spring Validation)
    Class<? extends Payload>[] payload() default {};
}