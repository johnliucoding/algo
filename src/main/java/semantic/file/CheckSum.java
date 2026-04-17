package semantic.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author liuxiaodong02
 */
public class CheckSum {

    // Compute a 16-bit checksum for all the remaining bytes
    // in the given byte buffer
    private static int sum(ByteBuffer bb) {
        int sum = 0;
        while (bb.hasRemaining()) {
            if ((sum & 1) != 0)
                sum = (sum >> 1) + 0x8000;
            else
                sum >>= 1;
            sum += bb.get() & 0xff;
            sum &= 0xffff;
        }
        return sum;
    }

    // Compute and print a checksum for the given file

    private static void sum(Path path) throws IOException {

        // Open the file and then get a channel from the stream
        try (FileChannel fc = FileChannel.open(path, StandardOpenOption.READ)) {

            // Get the file's size and then map it into memory
            int sz = (int) fc.size();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

            // Compute and print the checksum
            int sum = sum(bb);
            int kb = (sz + 1023) / 1024;
            String s = Integer.toString(sum);
            System.out.println(s + "\t" + kb + "\t" + path);
        }
    }

    static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java CheckSum file...");
            return;
        }
        for (String arg : args) {
            Path path = Path.of(arg);
            try {
                sum(path);
            } catch (IOException e) {
                System.err.println(path + ": " + e);
            }
        }
    }
}