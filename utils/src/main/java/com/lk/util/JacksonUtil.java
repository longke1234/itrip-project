package com.lk.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JacksonUtil {

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule())
                .getSerializerProvider()
                .setNullValueSerializer(new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        gen.writeString("");
                    }
                });
        return objectMapper;
    }

    /**
     * 将object对象转换成json类型
     * @param object
     * @return
     */
    public static String objectToJsonString(Object object) {
        ObjectMapper objectMapper = createObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 将json字符串反序列化Object
     * @param jsonString 待反序列化的json自负串
     * @param tClass 返回的类类型
     * @param <T> 泛型
     * @return
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> tClass) {
        ObjectMapper objectMapper = createObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(jsonString, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

}
