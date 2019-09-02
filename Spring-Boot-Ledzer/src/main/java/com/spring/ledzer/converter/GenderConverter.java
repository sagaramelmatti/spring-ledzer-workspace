package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.Gender;


@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
 
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getShortName();
    }
 
    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.fromShortName(dbData);
    }
 
}


