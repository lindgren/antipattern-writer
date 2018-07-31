package uk.co.lindgrens.carlosStyle;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GraphiteClient {

    private final int graphitePort;
    private final String graphiteHost;

    public GraphiteClient(String graphiteHost, int graphitePort) {
        this.graphiteHost = graphiteHost;
        this.graphitePort = graphitePort;
    }

    public void sendMetric(String key, String value, long timestamp) {

        String message = createMessage(key, value, timestamp);
        send(message);

    }

    private void send(String message) {

        try (
                Socket socket = createSocket();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.write(message);

        } catch (IOException e) {
            //Custom exception here
            throw new RuntimeException("Error while sending message: " + e.getMessage(), e);
        }
    }

    private String createMessage(String key, String value, long timestamp) {
        return String.format("%s %s %d%n", key, value, timestamp);
    }

    private Socket createSocket() throws IOException {
        return new Socket(graphiteHost, graphitePort);
    }
}
