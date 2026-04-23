package semantic.file.operations;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;
import static java.nio.file.attribute.PosixFilePermission.GROUP_READ;

public class FileOperations {

    public static void writingStrings() {

        var logFile = Path.of(System.getProperty("user.dir"), "/my.log");
        try (var writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8,
                StandardOpenOption.APPEND)) {
            writer.write("This is the first line");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void copy() {
        var input = Path.of("input.txt");
        var output = Path.of("output.txt");
        try {
            Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(input);
            Files.size(output);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void countingWords() {
        var alice = Path.of("alice.txt");
        try(Stream<String> lines = Files.lines(alice))  {
            long count = lines.flatMap(line -> Stream.of(line.split("")))
                    .filter(word -> word.equals("a"))
                    .count();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void tempFile() {
        try {
            Path p = Files.createTempFile("myapp", null);
            try (var output = Files.newOutputStream(p, DELETE_ON_CLOSE)) {
                // ...
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void manipulateFileAttributes() {
        try {
            var teamList = Path.of("/Users/ben/sample.txt");
            PosixFileAttributes attrs =
                    Files.readAttributes(teamList, PosixFileAttributes.class);

            Set<PosixFilePermission> posixFilePermissions = attrs.permissions();

            var owner = attrs.owner().getName();
            var perms = PosixFilePermissions.toString(posixFilePermissions);
            System.out.format("%s %s%n", owner, perms);

            posixFilePermissions.add(GROUP_READ);
            Files.setPosixFilePermissions(teamList, posixFilePermissions);
        } catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Path unpackJar(String zipFilePath) throws IOException {
        var tmpDir = Files.createTempDirectory(Path.of("/tmp"), "jar-extract");

        try (var zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            var entry = zipIn.getNextEntry();

            while (entry != null) {
                var newFile = tmpDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectory(newFile);
                } else {
                    Files.copy(zipIn, newFile);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }

        return tmpDir;
    }

}
