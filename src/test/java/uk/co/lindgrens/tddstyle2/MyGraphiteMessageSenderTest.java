package uk.co.lindgrens.tddstyle2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MyGraphiteMessageSenderTest {
    private MyGraphiteMessageSender messageSender;

    @Mock
    private PrintWriter printWriterMock;

    @BeforeEach
    public void setup() {
        messageSender = new MyGraphiteMessageSender("myhost", 2013) {
            PrintWriter getPrintWriter(OutputStream out) {
                return printWriterMock;
            }

            Socket createSocket() {
                return new Socket();
            }

            OutputStream getOutputStream(Socket socket) {
                return new ByteArrayOutputStream();
            }
        };
    }

    @Test
    public void should() {
        String key = "mykey";
        String value = "myvalue";
        long timestamp = 1532945352L;

        messageSender.sendMessage(key, value, timestamp);

        verify(printWriterMock, times(1)).printf("%s %s %d%n", key, value, timestamp);
    }
}
