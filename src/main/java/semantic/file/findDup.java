package semantic.file;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;


public class findDup {

    static void usage() {
        String usage = """
                java -jar findDup.jar [options] [directories...]
                
                -h --help              print usage
                -nr --nonrecursive     not recur to subdirectory
                """;
        System.err.println(usage);
        System.exit(1);
    }

    static void main(String[] args) {

        // wrong number of args
        if (args.length == 0) {
            System.err.println("wrong number of arguments");
            usage();
            return;
        }
        // print help
        List<String> argumentList = Arrays.asList(args);
        if (argumentList.contains("-h") || argumentList.contains("--help")) {
            usage();
            return;
        }

        boolean recursive = true;
        List<String> directories = new ArrayList<>();
        for (String s : argumentList) {
            if (s.equals("-nr") || s.equals("--nonrecursive")) {
                recursive = false;
            } else if (s.startsWith("-")) {
                System.err.println("wrong option: " + s);
                usage();
                return;
            } else {
                directories.add(s);
            }
        }
        System.out.println("Recursive: " + recursive);
        System.out.println("Candidate Directories: " + directories);

        List<Path> dirs = new ArrayList<>();
        List<String> nonExists = new ArrayList<>();
        for (String directory : directories) {
            Path path = Path.of(directory);
            if (Files.exists(path) && Files.isDirectory(path)) {
                dirs.add(path);
            } else {
                nonExists.add(directory);
            }
        }

        if (!nonExists.isEmpty()) {
            System.err.println("Those Directories Not Found: " + nonExists);
            usage();
            return;
        }

        // file size => path
        Map<Long, List<Path>> sizeMap = new HashMap<>();
        for (Path dir : dirs) {
            try (Stream<Path> pathStream = Files.walk(dir, recursive ? Integer.MAX_VALUE : 1)) {
                pathStream
                        .filter(Files::isRegularFile)
                        .filter(p -> {
                            try {
                                return !Files.isHidden(p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .forEach(path -> {
                            try {
                                long size = Files.size(path);
                                sizeMap.computeIfAbsent(size, k -> new ArrayList<>()).add(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Map<Long, List<Path>> checkSumMap = new HashMap<>();
        sizeMap.entrySet().stream()
                .filter(e -> e.getValue().size() > 1) // 多个文件有相同的文件大小
                .flatMap(e -> e.getValue().stream())
                .forEach(path -> {
                    long checkSum = checkSum(path);
                    checkSumMap.computeIfAbsent(checkSum, k -> new ArrayList<>()).add(path);
                });

        Map<Long, List<Path>> dupFiles = checkSumMap.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (dupFiles.isEmpty()) {
            System.out.println("No duplicate files found");
        } else {
            dupFiles.values()
                    .forEach(paths -> {
                        System.out.println("These files are the same:");
                        paths.sort(Comparator.comparing(Path::getFileName));
                        paths.forEach(path -> {
                            System.out.println("\t" + humanFileSize(path) + "\t" + path);
                        });
                    });
        }
        System.out.println("Number of duplicates found: " + dupFiles.size());

    }

    private static String humanFileSize(Path path) {
        try {
            long size = Files.size(path);
            if (size < 1024) {
                return size + "B";
            } else if (size < 1024 * 1024) {
                return size / 1024 + "KB";
            } else if (size < 1024 * 1024 * 1024) {
                return size / 1024 / 1024 + "MB";
            } else {
                return size / 1024 / 1024 / 1024 + "GB";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long checkSum(Path file) {
        try (CheckedInputStream cis =
                     new CheckedInputStream(
                             new BufferedInputStream(
                                     Files.newInputStream(file)), new CRC32())) {
            byte[] buff = new byte[4 * 1024];
            while (cis.read(buff) > 0) {
            }
            return cis.getChecksum().getValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
