package com.spring.auth.shared.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper {
    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    public static List<String> toStringList(String json) {
        List<String> object = new ArrayList<>();
        ObjectMapper objectMapper = getObjectMapper();

        try {
            object = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            // do nothing
        }

        return object;
    }

    public static String toString(List<String> jsonObject) {
        String value = "";
        ObjectMapper objectMapper = getObjectMapper();

        try {
            value = objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            // do nothing
        }

        return value;
    }
}
