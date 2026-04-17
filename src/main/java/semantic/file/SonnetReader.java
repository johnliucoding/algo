package semantic.file;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author liuxiaodong02
 */
public class SonnetReader {

    static void main() throws URISyntaxException {
        Path path = Path.of(SonnetTransformer.class.getClassLoader()
                .getResource("semantic/sonnets.bin").toURI());

        System.out.println(path.toString());

        try (InputStream in = Files.newInputStream(path,  StandardOpenOption.READ);
        BufferedInputStream bis = new BufferedInputStream(in);
        DataInputStream dis = new DataInputStream(bis)) {
            int numberOfSonnets = dis.readInt();
            System.out.println("numberOfSonnets: " + numberOfSonnets);
            List<Integer> offsets = new ArrayList<>();
            List<Integer> lengths = new ArrayList<>();
            for(int i = 0; i < numberOfSonnets; i++) {
                offsets.add(dis.readInt());
                lengths.add(dis.readInt());
            }
            bis.mark(Integer.MAX_VALUE);


             // there is a internal position
            bis.reset();
            int sonnet = 75; // the sonnet you are reading
            int offset = offsets.get(sonnet - 1);
            int length = lengths.get(sonnet - 1);

            skip(bis, offset);
            byte[] bytes = readBytes(bis, length);

            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                 GZIPInputStream gzbais = new GZIPInputStream(bais);
                 InputStreamReader isr = new InputStreamReader(gzbais);
                 BufferedReader reader = new BufferedReader(isr);) {
                List<String> sonnetLines = reader.lines().toList();
                sonnetLines.forEach(IO::println);
            }

            bis.reset();
            int sonnet2 = 75; // the sonnet you are reading
            int offset2 = offsets.get(sonnet2 - 1);
            int length2 = lengths.get(sonnet2 - 1);

            skip(bis, offset2);
            byte[] bytes2 = readBytes(bis, length2);

            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
                 GZIPInputStream gzbais = new GZIPInputStream(bais);
                 InputStreamReader isr = new InputStreamReader(gzbais);
                 BufferedReader reader = new BufferedReader(isr);) {
                List<String> sonnetLines = reader.lines().toList();
                sonnetLines.forEach(IO::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static long skip(BufferedInputStream bis, int offset) throws IOException {
        long skip = 0L;
        while (skip < offset) {
            skip += bis.skip(offset - skip);
        }
        return skip;
    }

    static byte[] readBytes(BufferedInputStream bis, int length) throws IOException {
        byte[] bytes = new byte[length];
        byte[] buffer = new byte[length];
        int read = bis.read(buffer);
        int copied = 0;
        while (copied < length) {
            System.arraycopy(buffer, 0, bytes, copied, read);
            copied += read;
            read = bis.read(buffer);
        }
        return bytes;
    }

}
