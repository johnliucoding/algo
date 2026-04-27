package semantic.socketChannel;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class Server02 {

    private static final ConcurrentMap<SocketChannel, ByteBuffer> map = new ConcurrentHashMap<>();

    static void main(String[] args) throws IOException {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            try (Selector selector = Selector.open()) {
                ssc.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
                        SelectionKey key = it.next();
                        try {
                            if (key.isValid()) {
                                if (key.isAcceptable()) {
                                    accept(key);
                                } else if (key.isReadable()) {
                                    read(key);
                                } else if (key.isWritable()) {
                                    write(key);
                                }
                            }
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                        it.remove();
                    }
                    map.keySet().removeIf(socketChannel -> !socketChannel.isOpen());
                }
            }
        }
    }

    private static void write(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buff = map.get(sc);
        sc.write(buff);
        if (!buff.hasRemaining()) {
            buff.compact();
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    private static void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buff = map.get(sc);
        int data = sc.read(buff);
        if (data == -1) {
            sc.close();
            map.remove(sc);
        }
        buff.flip();
        transmogrify(buff);
        key.interestOps(SelectionKey.OP_WRITE);

    }

    private static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept(); // not block, never null
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
        map.put(sc, ByteBuffer.allocate(1024));
    }


    private static void transmogrify(ByteBuffer buff) {
        for (int i = 0; i < buff.limit(); i++) {
            buff.put(i, (byte) transmogrify(buff.get(i)));
        }
    }

    private static int transmogrify(int data) {
        if (Character.isLetter(data)) return data ^ ' ';
        return data;
    }
}
