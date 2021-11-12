package com.example;


import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, Status.class, Status::fromStringOrNull);
    }

    @Bean
    public Module novaRestJacksonModule() {
        SimpleModule module = new SimpleModule("NovaRestModule");
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(DateTime.class, new DateTimeSerializer());
        module.addDeserializer(DateTime.class, new DateTimeDeserializer());
        return module;
    }


}
