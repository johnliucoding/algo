package semantic.file.operations;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelOperation {

    public static void fileRead() {
        boolean fileOK = true;
        try (FileChannel fchan = FileChannel.open(Path.of(""),StandardOpenOption.READ)) {
            var buffy = ByteBuffer.allocateDirect(16 * 1024 * 1024);

            while(fchan.read(buffy) != -1 || buffy.position() > 0 || fileOK) {
                fileOK = computeChecksum(buffy);
                buffy.compact();
            }

        } catch (IOException e) {
            System.out.println("Exception in I/O");
        }
    }

    private static boolean computeChecksum(ByteBuffer buffy) {
        return false;
    }

    // mmap
    public static void mappedFile() throws IOException {
        try (var fc = FileChannel.open(Path.of(""),StandardOpenOption.READ, StandardOpenOption.WRITE)) {

            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
            byte[] b = new byte[(int) fc.size()];

            mbf.get(b, 0, b.length);

            // Zero the in-memory copy
            for (int i = 0; i < fc.size(); i = i + 1) {
                b[i] = 0;
            }

            // Reposition to the start of the file
            mbf.position(0);

            // Zero the file
            mbf.put(b);
        }
    }

    // sendfile
    public static void sendFile() throws IOException {
        Path sourcePath = Paths.get("source.txt");
        Path destinationPath = Paths.get("destination.txt");
        try (FileChannel sourceChannel = FileChannel.open(sourcePath, java.nio.file.StandardOpenOption.READ);
             FileChannel destinationChannel = FileChannel.open(destinationPath, java.nio.file.StandardOpenOption.WRITE)) {
            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }
}
