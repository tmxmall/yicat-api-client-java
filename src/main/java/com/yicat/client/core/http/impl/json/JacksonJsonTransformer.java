package com.yicat.client.core.http.impl.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yicat.client.core.http.JsonTransformer;
import com.yicat.client.core.http.exceptions.HttpBadRequestException;
import com.yicat.client.core.http.exceptions.HttpException;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper;
    private final ObjectMapper errorObjectMapper;

    public JacksonJsonTransformer() {
        ObjectMapper cleanObjectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule enumModule = new SimpleModule()
            .addDeserializer(Enum.class, new EnumDeserializer());

        SimpleModule module = new SimpleModule();
        this.objectMapper = cleanObjectMapper.copy()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+hh:mm"))
                .registerModule(module)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.errorObjectMapper = cleanObjectMapper;
    }

    @Override
    @SneakyThrows
    public <T> T parse(String json, Class<T> clazz) {
        if (clazz.equals(HttpException.class) || clazz.equals(HttpBadRequestException.class)) {
            return this.errorObjectMapper.readValue(json, clazz);
        }
        return this.objectMapper.readValue(json, clazz);
    }

    @Override
    @SneakyThrows
    public <T> String convert(T obj) {
        return this.objectMapper.writeValueAsString(obj);
    }

}
