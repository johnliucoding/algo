package semantic.socketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server01 {

    static void main(String[] args) throws IOException {

        try(ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress("localhost", 8080));
            try (ExecutorService pool = Executors.newFixedThreadPool(1000)) {
                while (true) {
                    SocketChannel s = ssc.accept(); // blocking call - never return null
                    pool.submit(() -> {  // thread per connection, thread count is not limited, but system has a limit
                        process(s); // connected client does not affect others
                    });
                }
            }
        }

    }

    private static void process(SocketChannel sc) {
        System.out.println("Connection from " + sc);
        // position == 0
        // limit == capacity == 1024
        try{

            ByteBuffer buf = ByteBuffer.allocateDirect(1024);
            while (sc.read(buf) != -1) {
                // byte array with some additional information
                // position == 11
                // limit == 1024
                // capacity == 1024
                buf.flip(); // buf.limit(buf.position()).position(0)
                for(int i = 0; i < buf.limit(); i ++) {
                    buf.put(i, (byte) transmogrify(buf.get(i)));
                }
                while(buf.hasRemaining()) {
                    sc.write(buf);
                }
                buf.compact();
            }
        } catch (IOException e) {
            System.err.println("Connection problem -" + e);
        }
    }

    private static int transmogrify(int data) {
        if(Character.isLetter(data)) return data ^ ' ';
        return data;
    }
}
