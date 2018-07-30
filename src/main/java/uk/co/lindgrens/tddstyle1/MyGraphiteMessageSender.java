package uk.co.lindgrens.tddstyle1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyGraphiteMessageSender {
    private final MyGraphiteSocketFactory socketFactory;
    private final MyGraphitePrintWriter myGraphitePrintWriter;

    public MyGraphiteMessageSender(MyGraphiteSocketFactory socketFactory, MyGraphitePrintWriter myGraphitePrintWriter) {
        this.socketFactory = socketFactory;
        this.myGraphitePrintWriter = myGraphitePrintWriter;
    }

    public void sendMessage(String key, String value, long timestamp) {
        try {
            Socket socket = createSocket();
            OutputStream outputStream = socket.getOutputStream();

            myGraphitePrintWriter.write(outputStream, key, value, timestamp);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Add some messaging here",e);
        }
    }

    private Socket createSocket() throws IOException {
        return socketFactory.createSocket();
    }
}
