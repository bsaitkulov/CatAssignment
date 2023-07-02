package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertJavaToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
