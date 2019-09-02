package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.Status;


@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
 
    @Override
    public String convertToDatabaseColumn(Status Status) {
        return Status.getShortName();
    }
 
    @Override
    public Status convertToEntityAttribute(String dbData) {
        return Status.fromShortName(dbData);
    }
 
}


