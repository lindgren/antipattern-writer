package uk.co.lindgrens.tddstyle1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class MyGraphitePrintWriterTest {
    private MyGraphitePrintWriter myGraphitePrintWriter;

    @BeforeEach
    public void setup() {
        myGraphitePrintWriter = new MyGraphitePrintWriter();
    }

    @Test
    public void shouldWriteFormattedGraphiteMessage() {
        String key = "mykey";
        String value = "myvalue";
        long timestamp = 1532945352L;

        OutputStream outputStream = new ByteArrayOutputStream();

        myGraphitePrintWriter.write(outputStream, key, value, timestamp);

        assertEquals(outputStream.toString(), String.format("%s %s %d%n", key, value, timestamp));
    }
}