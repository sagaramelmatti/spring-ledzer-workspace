package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.AccountType;


@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {
 
    @Override
    public String convertToDatabaseColumn(AccountType AccountType) {
        return AccountType.getShortName();
    }
 
    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        return AccountType.fromShortName(dbData);
    }
 
}


