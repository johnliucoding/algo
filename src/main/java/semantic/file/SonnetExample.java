package semantic.file;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author liuxiaodong02
 */
public class SonnetExample {

    public static InputStream sonnetStream() {
        URI sonnetsURI = URI.create("https://www.gutenberg.org/cache/epub/1041/pg1041.txt");
        HttpRequest request =
                HttpRequest.newBuilder(sonnetsURI)
                        .GET()
                        .build();
        HttpClient client =
                HttpClient.newBuilder().build();
        try(client) {
            HttpResponse<InputStream> response =
                    client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class SonnetReader extends BufferedReader {

        public SonnetReader(Reader reader) {
            super(reader);
        }

        public SonnetReader(InputStream inputStream) {
            this(new InputStreamReader(inputStream));
        }

        public void skipLines(int lines) throws IOException {
            for (int i = 0; i < lines; i++) {
                readLine();
            }
        }

        private String skipSonnetHeader() throws IOException {
            String line = readLine();
            while (line.isBlank()) {
                line = readLine();
            }
            if (line.startsWith("*** END OF THE PROJECT GUTENBERG EBOOK")) {
                return null;
            }
            line = readLine();
            while (line.isBlank()) {
                line = readLine();
            }
            return line;
        }

        public Sonnet readNextSonnet() throws IOException {
            String line = skipSonnetHeader();
            if (line == null) {
                return null;
            } else {
                var sonnet = new Sonnet();
                while (!line.isBlank()) {
                    sonnet.add(line);
                    line = readLine();
                }
                return sonnet;
            }
        }
    }
    static class Sonnet {
        private List<String> lines = new ArrayList<>();

        public void add(String line) {
            lines.add(line);
        }

        public byte[] getCompressedBytes() {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (GZIPOutputStream gzos = new GZIPOutputStream(bos);
                 PrintWriter printWriter = new PrintWriter(gzos);) {

                for (String line : lines) {
                    printWriter.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return bos.toByteArray();
        }

    }

    public static List<Sonnet> sonnets(InputStream inputStream) {
        int start = 33;

        List<Sonnet> sonnets = new ArrayList<>();

        try (var reader = new SonnetReader(inputStream);
        ) {
            reader.skipLines(start);
            Sonnet sonnet = reader.readNextSonnet();
            while (sonnet != null) {
                sonnets.add(sonnet);
                sonnet = reader.readNextSonnet();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        IO.println("# sonnets = " + sonnets.size());
        return sonnets;
    }


    public static void writeAllSonnets(List<Sonnet> sonnets) {
        int numberOfSonnets = sonnets.size();
        Path sonnetsFile = Path.of("files/sonnets.bin");
        try (var sonnetFile = Files.newOutputStream(sonnetsFile);
             var dos = new DataOutputStream(sonnetFile);) {

            List<Integer> offsets = new ArrayList<>();
            List<Integer> lengths = new ArrayList<>();
            byte[] encodeSonnetsBytesArray = null;

            try (ByteArrayOutputStream encodedSonnets = new ByteArrayOutputStream()) {
                for (Sonnet sonnet : sonnets) {
                    byte[] sonnetCompressedBytes = sonnet.getCompressedBytes();
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readSingleSonnet() {
        Path path = Path.of("files/sonnets.bin");

        try (var file = Files.newInputStream(path);
             var bis = new BufferedInputStream(file);
             var dos = new DataInputStream(file);) {

            int numberOfSonnets = dos.readInt();
            IO.println("numberOfSonnets = " + numberOfSonnets);
            List<Integer> offsets = new ArrayList<>();
            List<Integer> lengths = new ArrayList<>();
            for(int i = 0; i < numberOfSonnets; i++) {
                offsets.add(dos.readInt());
                lengths.add(dos.readInt());
            }

            // At this point, you have the offests and the lengths of
            // all the sonnets
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
