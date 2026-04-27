package semantic.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author liuxiaodong02
 */
public class Cat {
    static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.println("need to specify a file to cat. example: java Cat.java /etc/passwd");
            System.exit(1);
        }

        Path path = Path.of(args[0]);
        // Files.copy(path, System.out)
        try(var file = Files.newByteChannel(path, StandardOpenOption.READ);
            var out = Channels.newChannel(System.out)) {
            var buffer = ByteBuffer.allocateDirect(1024);
            copy(file, out, buffer);

//            while (true) {
//                // read
//                var read = file.read(buffer);
//                if(read <= 0) break;
//                buffer.flip();
//                // write
//                var write = out.write(buffer);
//                if(write <= 0) break;
//                buffer.clear();
//            }
        }
    }

    static void copy(ReadableByteChannel from, WritableByteChannel to, ByteBuffer buffer) throws IOException {
        while (from.read(buffer) != -1) {
            buffer.flip();
            // write all data to out
            while(buffer.hasRemaining()) {
                to.write(buffer);
            }
            buffer.clear();
        }
    }

    static void copy2(ReadableByteChannel from, WritableByteChannel to, ByteBuffer buffer) throws IOException {
        while (from.read(buffer) != -1 || buffer.position() != 0) {
            buffer.flip();
            to.write(buffer);
            buffer.compact();
        }
    }
}
