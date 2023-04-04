package com.yicat.client.core.http;

import com.yicat.client.core.http.impl.json.JacksonJsonTransformer;
import com.yicat.client.core.model.EnumConverter;

/**
 * Json serializer/deserializer.
 * Default implementation {@link JacksonJsonTransformer} uses jackson json library.
 * For proper enum serialization/deserialization please take a look at {@link EnumConverter}
 */
public interface JsonTransformer {

    /**
     * This method will always work with non-generic java classes like {@link }.
     * This was done in order to avoid any library-specific annotations and hard dependency on library itself.
     * And for implementation developer can use any library without any issues with generics.
     *
     * @param json  json
     * @param clazz return type
     * @param <T>   return type
     * @return java class instance
     */
    <T> T parse(String json, Class<T> clazz);


    /**
     * @param obj object to convert
     * @param <T> object type
     * @return json string
     */
    <T> String convert(T obj);
}
