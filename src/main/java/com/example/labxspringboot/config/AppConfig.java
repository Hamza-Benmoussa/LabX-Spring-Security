package com.example.labxspringboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("com.example.labxspringboot")
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =new ModelMapper();
        Converter<Object, List<?>> mergingCollectionConverter = new AbstractConverter<Object, List<?>>() {
            @Override
            protected List<?> convert(Object source) {
                return modelMapper.map(source, List.class);
            }
        };

        modelMapper.addConverter(mergingCollectionConverter);

        return modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

}
