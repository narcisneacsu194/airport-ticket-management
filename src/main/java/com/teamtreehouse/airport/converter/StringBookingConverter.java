package com.teamtreehouse.airport.converter;

import com.teamtreehouse.airport.dao.BookingDao;
import com.teamtreehouse.airport.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StringBookingConverter implements Converter<String, Booking>{

    @Autowired
    private BookingDao bookingDao;

    @Override
    public Booking convert(String source){
        return bookingDao.findById(new Long(source));
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new StringBookingConverter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean.getObject();
    }
}
