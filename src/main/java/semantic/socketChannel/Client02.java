package semantic.socketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author liuxiaodong02
 */
public class Client02 {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
            sc.connect(addr);
            while (!sc.finishConnect()) {
                System.out.println("waiting to finish connection");
            }
            ByteBuffer buffer = ByteBuffer.allocate(200);
            while (sc.read(buffer) >= 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
