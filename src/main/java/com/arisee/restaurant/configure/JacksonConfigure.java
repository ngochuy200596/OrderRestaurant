package com.arisee.restaurant.configure;

import com.arisee.restaurant.jackson.LocalDateTimeJsonDeserializer;
import com.arisee.restaurant.jackson.LocalDateTimeJsonSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;

@Configuration
@SuppressWarnings("unused")
public class JacksonConfigure {
    @Bean
    public Jackson2ObjectMapperBuilder getObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.modules(new JavaTimeModule());

        builder.serializerByType(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeJsonDeserializer());
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        builder.failOnUnknownProperties(false);
        builder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

        builder.featuresToEnable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        builder.featuresToEnable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);

        return builder;
    }
}
