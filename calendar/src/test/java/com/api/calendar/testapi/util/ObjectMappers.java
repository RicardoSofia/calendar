package com.api.calendar.testapi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMappers {

    private ObjectMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static final ObjectMapper MAPPER_SIMPLE = new ObjectMapper().registerModule(
        new ParameterNamesModule()).registerModule(new Jdk8Module()).registerModule(new JavaTimeModule()).findAndRegisterModules();

    public static final ObjectMapper MAPPER_DATE_AWARE =  new ObjectMapper().setSerializationInclusion(
        JsonInclude.Include.NON_DEFAULT).registerModule(new JavaTimeModule()).disable(
        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).findAndRegisterModules();
}
