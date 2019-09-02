package com.spring.ledzer.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.spring.ledzer.model.PaymentType;


@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, String> {
 
    @Override
    public String convertToDatabaseColumn(PaymentType paymentType) {
        return paymentType.getShortName();
    }
 
    @Override
    public PaymentType convertToEntityAttribute(String dbData) {
        return PaymentType.fromShortName(dbData);
    }
 
}


