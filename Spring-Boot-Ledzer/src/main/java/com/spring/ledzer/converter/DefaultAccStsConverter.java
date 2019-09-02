package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.DefaultAccSts;


@Converter(autoApply = true)
public class DefaultAccStsConverter implements AttributeConverter<DefaultAccSts, String> {
 
    @Override
    public String convertToDatabaseColumn(DefaultAccSts DefaultAccSts) {
        return DefaultAccSts.getShortName();
    }
 
    @Override
    public DefaultAccSts convertToEntityAttribute(String dbData) {
        return DefaultAccSts.fromShortName(dbData);
    }
 
}


