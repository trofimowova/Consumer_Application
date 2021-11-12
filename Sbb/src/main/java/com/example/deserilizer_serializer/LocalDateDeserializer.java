package com.example.deserilizer_serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter ISO_LOCAL_DATE = ISODateTimeFormat.date();

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LocalDate result = null;
        String jsonText = jsonParser.getText();
        if (!StringUtils.isEmpty(jsonText)) {
            result = ISO_LOCAL_DATE.parseLocalDate(jsonText);
        }
        return result;
    }
}
