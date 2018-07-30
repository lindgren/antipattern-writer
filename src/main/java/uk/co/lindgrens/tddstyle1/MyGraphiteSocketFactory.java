package uk.co.lindgrens.tddstyle1;

import java.io.IOException;
import java.net.Socket;

public class MyGraphiteSocketFactory {
    private final String host;
    private final int port;

    public MyGraphiteSocketFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket createSocket() throws IOException {
        return new Socket(host, port);
    }
}
