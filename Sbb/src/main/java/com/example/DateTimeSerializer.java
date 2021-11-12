package com.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;


public class DateTimeSerializer extends StdSerializer<DateTime> {

    private static final long serialVersionUID = -1665313336276336682L;
    private static final DateTimeFormatter ISO_DATE_TIME = ISODateTimeFormat.dateTimeNoMillis();

    public DateTimeSerializer() {
        super(DateTime.class);
    }

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String stringRepresentation = ISO_DATE_TIME.print(dateTime);
        jsonGenerator.writeString(stringRepresentation);
    }
}