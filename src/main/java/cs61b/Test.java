package modern;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.util.concurrent.Future;

/**
 * @author Liu Xiaodong
 * @date 2024/3/11 11:42 AM
 */
public class Test {
    public static void main(String[] args) throws IOException {

        final AsynchronousFileChannel file = AsynchronousFileChannel.open(Path.of("primes.txt"));
        try (file) {
            final Future<Integer> readResult = file
                    .read(ByteBuffer.allocate(1024), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
