package uk.co.lindgrens.carlosStyle;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGraphiteMessageSenderTest {

    @Test
    void should_send_message() throws IOException {

        ServerSocket serverSocket = new ServerSocket(0);
        MyGraphiteMessageSender sender = new MyGraphiteMessageSender("localhost", serverSocket.getLocalPort());

        sender.sendMessage("key", "value", 1234566L);

        BufferedReader input = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));

        String result = input.lines().collect(Collectors.joining());

        serverSocket.close();

        assertEquals("key value 1234566", result);

    }


}