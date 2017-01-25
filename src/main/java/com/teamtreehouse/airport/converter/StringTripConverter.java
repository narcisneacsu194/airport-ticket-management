package com.teamtreehouse.airport.converter;

import com.teamtreehouse.airport.dao.TripDao;
import com.teamtreehouse.airport.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringTripConverter implements Converter<String, Trip>{

    @Autowired
    private TripDao tripDao;

    @Override
    public Trip convert(String source){
        return tripDao.findById(new Long(source));
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new StringTripConverter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean.getObject();
    }
}
