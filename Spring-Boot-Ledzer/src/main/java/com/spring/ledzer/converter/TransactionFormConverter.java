package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.TransactionForm;


@Converter(autoApply = true)
public class TransactionFormConverter implements AttributeConverter<TransactionForm, String> {
 
    @Override
    public String convertToDatabaseColumn(TransactionForm transactionForm) {
        return transactionForm.getShortName();
    }
 
    @Override
    public TransactionForm convertToEntityAttribute(String dbData) {
        return TransactionForm.fromShortName(dbData);
    }
 
}


