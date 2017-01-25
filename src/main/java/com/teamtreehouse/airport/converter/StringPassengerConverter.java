package com.teamtreehouse.airport.converter;

import com.teamtreehouse.airport.dao.PassengerDao;
import com.teamtreehouse.airport.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringPassengerConverter implements Converter<String, Passenger>{

    @Autowired
    private PassengerDao passengerDao;

    @Override
    public Passenger convert(String source){
        return passengerDao.findById(new Long(source));
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new StringPassengerConverter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean.getObject();
    }
}
