package com.arisee.restaurant.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

@Slf4j
public class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        LocalDateTime rs = null;
        try {
            Long date = jp.getValueAsLong();
            date = date / 1000;
            rs = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
        }
        return rs;
    }
}
