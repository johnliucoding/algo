package semantic.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * @author liuxiaodong02
 */
public class Server02 {
    /**
     * A server program which accepts requests from clients to capitalize strings.
     * When a client connects, a new thread is started to handle it. Receiving
     * client data, capitalizing it, and sending the response back is all done on
     * the thread, allowing much greater throughput because more clients can be
     * handled concurrently.
     *
     * @throws IOException
     */
    static void main() throws IOException {
        try (var listener = new ServerSocket(59898)) {
            IO.println("The capitalization server is running...");
            try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
                while (true) {
                    Socket connection = listener.accept();
                    pool.execute(new Capitalizer(connection));
                }
            }
        }
    }

    static class Capitalizer implements Runnable {
        private final Socket socket;

        public Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            IO.println("Connected: " + socket);
            try (var in = new Scanner(socket.getInputStream())) {
                try (var out = new PrintWriter(socket.getOutputStream(), true)) {
                    while (in.hasNextLine()) {
                        out.println(in.nextLine().toUpperCase());
                    }
                }
            } catch (IOException e) {
                IO.println("Error: " + socket);
            } finally {
                IO.println("Disconnected: " + socket);
            }
        }
    }
}
