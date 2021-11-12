package com.example.deserilizer_serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = 154614334048405310L;
    private static final DateTimeFormatter ISO_LOCAL_DATE = ISODateTimeFormat.date();

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String stringRepresentation = ISO_LOCAL_DATE.print(localDate);
        jsonGenerator.writeString(stringRepresentation);
    }
}
