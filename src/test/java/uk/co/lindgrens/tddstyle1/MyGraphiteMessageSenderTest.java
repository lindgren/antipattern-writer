package uk.co.lindgrens.tddstyle1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.SocketFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyGraphiteMessageSenderTest {
    private MyGraphiteMessageSender myGraphiteMessageSender;

    @Mock
    private MyGraphiteSocketFactory socketFactory;

    @Mock
    private MyGraphitePrintWriter myGraphitePrintWriter;

    @Mock
    private Socket socket;

    @BeforeEach
    public void setup() {
        myGraphiteMessageSender = new MyGraphiteMessageSender(socketFactory, myGraphitePrintWriter);
    }

    @Test
    public void shouldSendMessage() throws IOException {
        // given
        String key = "mykey";
        String value = "myvalue";
        long timestamp = 1532945352L;
        OutputStream outputStream = new ByteArrayOutputStream();

        //when
        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socketFactory.createSocket()).thenReturn(socket);

        myGraphiteMessageSender.sendMessage(key, value, timestamp);

        //then
        verify(myGraphitePrintWriter, times(1)).write(outputStream, key, value, timestamp);
    }
}