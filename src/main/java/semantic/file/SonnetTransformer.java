package semantic.file;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;


/**
 * @author liuxiaodong02
 */
public class SonnetTransformer {

    public static boolean isRomanNumeral(String str) {
        String regex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return str != null && str.matches(regex);
    }

    public static List<List<String>> sonnets(Path path) throws IOException {
        try(Stream<String> lines = Files.lines(path)) {
            List<String> list = lines.dropWhile(line -> !line.startsWith("I"))
                    .takeWhile(line -> !line.startsWith("*** "))
//                    .peek(System.out::println)
                    .toList();
            List<List<String>> sonnets = new ArrayList<>();
            long counter = 0L;
            List<String> current = null;
            for (String line : list) {
                if (line.isEmpty()) continue;
                if (isRomanNumeral(line)) {
                    if (current != null) {
                        sonnets.add(current);
                    }
                    counter++;
                    current = new ArrayList<>();
                } else {
                    current.add(line);
                }
            }
            sonnets.add(current);

//            System.out.println(counter);
//            System.out.println(sonnets.size());
//            for (List<String> sonnet : sonnets) {
//                for (String s : sonnet) {
//
//                    System.out.printf("%s\n", s);
//                }
//                System.out.println("-----");
//
//            }
            return sonnets;

        }
    }

    public static byte[] getCompressedBytes(List<String> lines) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzos = new GZIPOutputStream(bos);
             PrintWriter printWriter = new PrintWriter(gzos);) {
            for (String line : lines) {
                printWriter.println(line);
            }
        }
        return bos.toByteArray();
    }

    static void main() throws IOException, URISyntaxException {

        Path path = Path.of(SonnetTransformer.class.getClassLoader()
                .getResource("semantic/pg1041.txt").toURI());

        System.out.println(path.toString());
        List<List<String>> sonnets = sonnets(path);

        int numberOfSonnets = sonnets.size();

        Path sonnetsPath = path.getParent().resolve(Path.of("sonnets.bin"));

        System.out.println(sonnetsPath.toString());
        try (var sonnetFile = Files.newOutputStream(sonnetsPath, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
             var dos = new DataOutputStream(sonnetFile)) {

            List<Integer> offsets = new ArrayList<>();
            List<Integer> lengths = new ArrayList<>();

            byte[] encodeSonnetsBytesArray;
            try (ByteArrayOutputStream encodedSonnets = new ByteArrayOutputStream()) {
                for (List<String> sonnet : sonnets) {
                    byte[] sonnetCompressedBytes =  getCompressedBytes(sonnet);
                    offsets.add(encodedSonnets.size());
                    lengths.add(sonnetCompressedBytes.length);
                    encodedSonnets.write(sonnetCompressedBytes);
                }

                dos.writeInt(numberOfSonnets);
                for (int index = 0; index < numberOfSonnets; index++) {
                    dos.writeInt(offsets.get(index));
                    dos.writeInt(lengths.get(index));
                }
                encodeSonnetsBytesArray = encodedSonnets.toByteArray();
            }
            sonnetFile.write(encodeSonnetsBytesArray);
        }
    }
}
