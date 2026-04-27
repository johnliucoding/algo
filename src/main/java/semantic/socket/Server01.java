package semantic.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.time.Instant;

/**
 * @author liuxiaodong02
 */
public class Server01 {

    /**
     * A simple TCP server. When a client connects, it sends the client the current
     * datetime, then closes the connection.
     *
     * @throws IOException
     */
    static void main() throws IOException {
        try (var listener = new ServerSocket(59090)) {
            IO.println("The data server is running...");
            while (true) {
                try (var socket = listener.accept()) {
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(Instant.now().toString());
                }
            }
        }
    }
}

// java Server01.java

// netstat -an | grep 59090

// nc localhost 59090
