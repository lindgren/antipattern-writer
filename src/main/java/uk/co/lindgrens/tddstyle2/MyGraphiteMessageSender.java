package uk.co.lindgrens.tddstyle2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyGraphiteMessageSender {
    private final int graphitePort;
    private final String graphiteHost;

    public MyGraphiteMessageSender(String graphiteHost, int graphitePort) {
        this.graphiteHost = graphiteHost;
        this.graphitePort = graphitePort;
    }

    public void sendMessage(String key, String value, long timestamp) {
        try {
            Socket socket = createSocket();
            OutputStream s = getOutputStream(socket);
            print(s, key, value, timestamp);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Unknown host: " + graphiteHost);
        }
    }

    Socket createSocket() throws IOException {
        return new Socket(graphiteHost, graphitePort);
    }

    OutputStream getOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    private void print(OutputStream outputStream, String key, String value, long timestamp) {
        PrintWriter out = getPrintWriter(outputStream);
        out.printf("%s %s %d%n", key, value, timestamp);
        out.close();
    }

    PrintWriter getPrintWriter(OutputStream o) {
        return new PrintWriter(o, true);
    }
}
