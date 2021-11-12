package com.example.deserilizer_serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

    private static final DateTimeFormatter ISO_DATE_TIME = ISODateTimeFormat.dateTimeParser();

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DateTime result = null;
        String jsonText = jsonParser.getText();
        if (!StringUtils.isEmpty(jsonText)) {
            result = ISO_DATE_TIME.parseDateTime(jsonText);
        }
        return result;
    }
}
