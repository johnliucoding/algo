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
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Handler;

/**
 * @author liuxiaodong02
 */
public class Server03 {

    static class Reactor implements Runnable {
        final Selector selector;
        final ServerSocketChannel serverSocketChannel;
        Reactor(int port) throws IOException {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            key.attach(new Acceptor());
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    for (Iterator<SelectionKey> it = selected.iterator(); it.hasNext(); ) {
                        SelectionKey key = it.next();
                        dispatch(key);
                    }
                    selected.clear();
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        private void dispatch(SelectionKey key) {
            Runnable r = (Runnable) key.attachment();
            if (r != null) {
                r.run();
            }
        }

        class Acceptor implements Runnable {
            @Override
            public void run() {
                try {
                    SocketChannel c = serverSocketChannel.accept();
                    if (c != null) {
                        new Handler(c, selector);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }

    }
    static class Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey key;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0;
        static final int SENDING = 1;
        int state = READING;
        Handler(SocketChannel socketChannel, Selector sel) throws IOException {
            socketChannel.configureBlocking(false);
            this.socketChannel = socketChannel;
            this.key = socketChannel.register(sel, 0);
            key.attach(this);
            key.interestOps(SelectionKey.OP_READ);
            sel.wakeup();

        }

        boolean inputIsComplete() {return true;}
        boolean outputIsComplete() {return true;}
        void process() {}

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else  if (state == SENDING) {
                    send();
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        void read() throws IOException {
            socketChannel.read(input);
            if(inputIsComplete()) {
                process();
                state = SENDING;
                // normally also do first write now
                key.interestOps(SelectionKey.OP_WRITE);
            }
        }
        void send() throws IOException {
            socketChannel.write(output);
            if(outputIsComplete()) {
                key.cancel();
            }
        }
    }

}
