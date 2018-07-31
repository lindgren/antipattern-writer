package uk.co.lindgrens.carlosStyle;

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
            OutputStream s = socket.getOutputStream();

            PrintWriter out = new PrintWriter(s, true);
            out.printf("%s %s %d%n", key, value, timestamp);
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Unknown host: " + graphiteHost);
        }
    }

    private Socket createSocket() throws IOException {
        return new Socket(graphiteHost, graphitePort);
    }
}
