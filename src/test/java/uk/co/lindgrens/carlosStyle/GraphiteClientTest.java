package uk.co.lindgrens.carlosStyle;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphiteClientTest {

    @Test
    void should_send_message() throws IOException {

        ServerSocket serverSocket = new ServerSocket(0);
        GraphiteClient sender = new GraphiteClient("localhost", serverSocket.getLocalPort());

        sender.sendMetric("key", "value", 1234566L);

        BufferedReader input = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));

        String result = input.lines().collect(Collectors.joining());

        serverSocket.close();

        assertEquals("key value 1234566", result);

    }

    @Test
    void should_throw_exception_when_hostname_cant_be_solved() {

        Exception e = assertThrows(RuntimeException.class, () -> {

            GraphiteClient sender = new GraphiteClient("wrong-hostname", 8080);

            sender.sendMetric("key", "value", 1234566L);

        });

        assertEquals("Error while sending message: wrong-hostname", e.getMessage());

    }

    @Test
    void should_throw_exception_when_port_is_wrong() {

        Exception e = assertThrows(RuntimeException.class, () -> {

            ServerSocket serverSocket = new ServerSocket(0);
            GraphiteClient sender = new GraphiteClient("localhost", serverSocket.getLocalPort() - 1);

            sender.sendMetric("key", "value", 1234566L);

            serverSocket.close();

        });

        assertEquals("Error while sending message: Connection refused (Connection refused)", e.getMessage());

    }

    @Test
    void should_throw_exception_when_server_is_closed() {

        Exception e = assertThrows(RuntimeException.class, () -> {

            ServerSocket serverSocket = new ServerSocket(0);
            GraphiteClient sender = new GraphiteClient("localhost", serverSocket.getLocalPort());
            serverSocket.close();

            sender.sendMetric("key", "value", 1234566L);

        });

        assertEquals("Error while sending message: Connection refused (Connection refused)", e.getMessage());

    }


}