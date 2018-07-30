package uk.co.lindgrens.tddstyle1;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MyGraphitePrintWriter {
    public void write(OutputStream outputStream, String key, String value, long timestamp) {
        PrintWriter out = new PrintWriter(outputStream, true);
        out.printf("%s %s %d%n", key, value, timestamp);
        out.close();
    }
}
