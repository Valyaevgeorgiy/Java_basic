package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TODO: что такое @Retention ?
@Retention(RetentionPolicy.RUNTIME)

//TODO: что такое @Target ?
@Target(ElementType.TYPE)
public @interface ProgrammingDetails {
    String programmerType();

    String programmerName();

    String dateOfWriting();

    String durationOfWriting() default "1 day";// Значение по умолчанию

    String[] reviewers();

}
